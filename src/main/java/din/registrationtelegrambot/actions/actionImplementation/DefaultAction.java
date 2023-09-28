package din.registrationtelegrambot.actions.actionImplementation;

import din.registrationtelegrambot.actions.Action;
import din.registrationtelegrambot.configuration.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class DefaultAction implements Action {
    @Override
    public SendMessage run(Message message, Bot bot) {
        System.out.println("default");
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("default");
        sendMessage.setChatId(message.getChatId());
        return sendMessage;
    }


}
