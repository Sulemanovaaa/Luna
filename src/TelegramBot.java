import com.sun.org.apache.regexp.internal.RE;
import entity.Recipe;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import services.DataService;
import services.MenuService;
import services.RecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {

   private static MenuService menuService;
   private static DataService dataService;
   private static RecipeService recipeService;
   private static DataInitializer dataInitializer = new DataInitializer();

   private List<Recipe> recipes;
   private boolean hasOrder = false;
   private Recipe currentDish;

   private static void init(){
       dataService = dataInitializer.initDataService();
       menuService = dataInitializer.initMenuService();
       recipeService = dataInitializer.initRecipeService();
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
            else if (message.getText().equals("Сделать заказ")) {
                showMenu(message);
                hasOrder = true;
            }
            else if (hasOrder && messageIsADish(message)) {
                sendMsg(message, "Начинаю готовить");
                startCooking(message);
            }
            else
                sendMsg(message, "Я не знаю что ответить на это");
        }
    }

    private void startCooking(Message message) {
        Map<Integer ,List<Integer>> stages = currentDish.getStages();

    }

    private boolean messageIsADish(Message message) {
        for (Recipe recipe: recipes) {
            if (message.getText().startsWith(recipe.getName())) {
                currentDish = recipe;
                return true;
            }
        }
        return false;
    }

    private void showMenu(Message message) {

        recipes = menuService.getMenu();

        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Вот что мы можем вам предложить");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (Recipe recipe: recipes) {
            KeyboardRow row = new KeyboardRow();
            row.add(recipe.getName() + " " + recipe.getCookingTime() + "с.");
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


