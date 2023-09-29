package din.registrationtelegrambot.configuration;

import din.registrationtelegrambot.entities.Client;
import din.registrationtelegrambot.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@AllArgsConstructor
public class Bot extends TelegramLongPollingBot {
    BotConfig botConfig;
    UserService userService;
    BotContext botContext;

    @Override
    public void onUpdateReceived(Update update) {

        BotStage botStage;
        if(!update.hasMessage() || !update.getMessage().hasText()){
            return;
        }

        String inputText = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        Client client = userService.getUserById(update.getMessage().getChatId());
        System.out.println(chatId + "onUpdateReceived" );
        if(client == null){
            client = new Client(chatId,
                    "din"
                    ,"start");

            userService.save(client);
            botContext.of(this,inputText, client);
            botStage = BotStage.getByTitle("start");
            botStage.onEntry(botContext);
            return;
        }
            botContext.of(this,inputText, client);
            botStage = BotStage.getByTitle(client.getTitleState());

           if(botStage.isInputNeeded()){
               botStage.onHandleInput(botContext);
           }

            botStage = botStage.nextBotStage(botContext);
            client.setTitleState(botStage.getTitleState());
            botStage.onEntry(botContext);
            userService.save(client);


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
