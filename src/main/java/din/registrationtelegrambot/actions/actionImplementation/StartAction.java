package din.registrationtelegrambot.actions.actionImplementation;

import din.registrationtelegrambot.actions.Action;
import din.registrationtelegrambot.configuration.Bot;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component("/start")
@Data
public class StartAction implements Action {
    boolean isWorking = true;
    @Override
    public SendMessage run(Message message, Bot bot) {
        System.out.println("StartAction");
        long id = message.getChatId();
        SendMessage sendMessage  = new SendMessage();
        sendMessage.setChatId(id);
        sendMessage.setText("Hi");
        return sendMessage;

    }
}

