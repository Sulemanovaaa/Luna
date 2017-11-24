import entity.*;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import services.*;

import java.util.*;

public class TelegramBot extends TelegramLongPollingBot {

    private static MenuService menuService;
    private static StorageService storageService;
    private static RecipeService recipeService;
    private static DishService dishService;
    private static CookService cookService;
    private static DataInitializer dataInitializer = new DataInitializer();
    private static Cook cook;

    private static void init(){
        storageService = dataInitializer.initDataService();
        menuService = dataInitializer.initMenuService();
        dishService = dataInitializer.initDishService();
        cookService = dataInitializer.initCookService();
        recipeService = dataInitializer.initRecipeService();
        cook = cookService.getCook();
    }

    public static void main(String args[]) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        init();
    }

    @Override
    public String getBotUsername() {
        return "Emotional_cookbot";
    }

    @Override
    public String getBotToken() {
        return "443161832:AAEBi1SVSD1NKXnuvTlYGOGm1BXyzuKs2BU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, я робот");
                sendMsg(message, "Чтобы увидеть меню напиши:");
                sendMsg(message, "Сделать заказ");
            }
            else if (cook.getState().equals(CookStates.FREE) &&  message.getText().equals("Сделать заказ")) {
                showMenu(message);
                cook.setState(CookStates.HAS_ORDER);
            }
            else if (cook.getState().equals(CookStates.COOKING) && messageIsAction(message)) {
                //то применяется экшн к повару
                cookService.checkReactions();

                //чек реактивных реакций
                //Эмоции повара применяются к блюду
            }
            else if (cook.getState().equals(CookStates.HAS_ORDER) && messageIsADish(message)) {
//                sendMsg(message, "Начинаю готовить");
                cook.setState(CookStates.COOKING);
                startCooking(message);
            }
            else
                sendMsg(message, "Я не знаю что ответить на это");
        }
    }

    private void startCooking(Message message) {

        if (recipeService.iteratorNext()) {
            Step step = recipeService.getCurrentStepInRecipe();
            showStepAndHisAction(message, step);

            //show steps by timer
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    startCooking(message);
                }
            };

            Timer timer = new Timer();
            timer.schedule(timerTask, step.getTime() * 1000);
        } else {
            cook.setState(CookStates.FREE);
        }

    }


    private void showStepAndHisAction(Message message, Step step) {
        List<Action> actions = recipeService.getAllActionsInCurrentStep();
        List<String> actionButtons = new ArrayList<>();
        for (Action action: actions) {
            actionButtons.add(action.getName());
        }

        showButtons(actionButtons, step.getName(), message);
    }

    private void showButtons(List<String> buttonsName, String textWithMessage, Message message) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText(textWithMessage);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String button: buttonsName) {
            KeyboardRow row = new KeyboardRow();
            row.add(button);
            keyboard.add(row);
        }

        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setOneTimeKeyboard(true);

        // Add it to the message
        sendMessage.setReplyMarkup(keyboardMarkup);
        try {
            sendMessage(sendMessage); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private boolean messageIsADish(Message message) {
        for (Recipe recipe: menuService.getMenu()) {
            if (message.getText().startsWith(recipe.getName())) {
                recipeService.setRecipe(recipe.getId());
                recipeService.initIterator();
                return true;
            }
        }
        return false;
    }

    private boolean messageIsAction(Message message) {
        for (Action action: recipeService.getAllActionsInCurrentStep()) {
            if (message.getText().startsWith(action.getName())) {
                applyActionOnCook(message, action);
                return true;
            }
        }
        return false;
    }

    private void applyActionOnCook(Message message, Action action) {
        getCookInfo(message);
        cookService.changeCookProperties(action);
        getCookInfo(message);
    }

    private void getCookInfo(Message message) {
        sendMsg(message,cookService.getCook().toString());
    }

    private void showMenu(Message message) {
        List<String> buttonsName = new  ArrayList<>();
        for (Recipe recipe: menuService.getMenu()) {
            buttonsName.add(recipe.getName() + " " + recipe.getCookingTime() + "c");
        }
        showButtons(buttonsName, "Вот что мы можем вам предложить", message);
    }

    private void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
