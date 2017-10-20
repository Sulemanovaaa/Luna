import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import services.RecipeService;
import utils.DirectoryUtil;
import utils.JsonUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    private RecipeService recipeService = new RecipeService(JsonUtil.jsonToObject(LinkedHashMap.class, DirectoryUtil.DICTIONARY_PATH));
    private List<String> recipeNames = recipeService.getMenu(DirectoryUtil.getRecipeFileNames(DirectoryUtil.RECIPES_PATH));

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
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
            }
            else if (messageIsADish(message)) {
                sendMsg(message, "Начинаю готовить");
            }
            else
                sendMsg(message, "Я не знаю что ответить на это");
        }
    }

    private boolean messageIsADish(Message message) {
        for (String recipe: recipeNames) {
            if (message.getText().equals(recipe)) {
                return true;
            }
        }
        return false;
    }

    private void showMenu(Message message) {

        SendMessage sendMessage = new SendMessage()
                .setChatId(message.getChatId())
                .setText("Вот что мы можем вам предложить");

        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String recipe: recipeNames) {
            KeyboardRow row = new KeyboardRow();
            row.add(recipe);
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


