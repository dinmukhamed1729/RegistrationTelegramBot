package din.registrationtelegrambot.actions;

import din.registrationtelegrambot.configuration.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface Action {
    SendMessage run(Message message, Bot bot);

}
