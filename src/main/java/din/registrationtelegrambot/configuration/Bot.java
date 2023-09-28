package din.registrationtelegrambot.configuration;
import din.registrationtelegrambot.actions.Action;
import din.registrationtelegrambot.actions.actionImplementation.DefaultAction;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

@Component
public class Bot extends TelegramLongPollingBot {
    public Action action;
    @Autowired
    BotConfig botConfig ;
    @Autowired
    DefaultAction defaultAction;
    @Autowired
    Map<String, Action> actions;
    @Override
    public void onUpdateReceived(Update update) {

    }

    private void sendToChat(Message message){
        try {
            execute(new SendMessage(message.getChatId().toString(),message.getText()));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    private void sendToChat(String text, long chatId)  {
        try {
            execute(new SendMessage(String.valueOf(chatId),text));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotToken() {
        return botConfig.getBotToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }

}
