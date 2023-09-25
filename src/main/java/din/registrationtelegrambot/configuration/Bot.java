package din.registrationtelegrambot.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class Bot extends TelegramLongPollingBot {
    private int countOfMessage = 0;
    @Autowired
    BotConfig botConfig ;
    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(countOfMessage);
        countOfMessage++;
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
