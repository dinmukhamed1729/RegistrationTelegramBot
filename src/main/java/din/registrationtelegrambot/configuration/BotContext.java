package din.registrationtelegrambot.configuration;


import din.registrationtelegrambot.entities.Client;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BotContext {
    private Bot bot;
    private String inputText;
    private Client client;
    public BotContext(){}
    public void of(Bot bot, String inputText, Client client){
        this.client = client;
        this.inputText = inputText;
        this.bot = bot;
    }

}
