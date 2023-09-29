package din.registrationtelegrambot.configuration;

import din.registrationtelegrambot.entities.Client;
import din.registrationtelegrambot.services.UserService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum BotStage {
    START("start"){
        @Override
        public void onEntry(BotContext botContext) {
            SendMessageToChat(botContext,"Hi, введите имя ");
        }

        @Override
        public BotStage onHandleInput(BotContext botContext) {
           Client client = botContext.getClient();
           client.setFirstName(botContext.getInputText());
            UserService userService = new UserService();
            userService.save(client);
            return START;
        }

        @Override
        public BotStage nextBotStage(BotContext botContext) {
            return AFTERREGISTRATION;
        }
    },
    AFTERREGISTRATION(true,"POST-REGISTRATION"){
        @Override
        public void onEntry(BotContext botContext) {
            SendMessageToChat(botContext, "вы зарегестрированы ");

        }
        @Override
        public BotStage onHandleInput(BotContext botContext) {
            SendMessageToChat(botContext, "вы зарегестрированы ");
            return DEFAULT;
        }

        @Override
        public BotStage nextBotStage(BotContext botContext) {
            return DEFAULT;
        }
    },
    DEFAULT("default"){
        @Override
        public void onEntry(BotContext botContext) {

        }

        @Override
        public BotStage onHandleInput(BotContext botContext) {
            return null;
        }

        @Override
        public BotStage nextBotStage(BotContext botContext) {
            return DEFAULT;
        }
    };

    public boolean isInputNeeded() {
        return InputNeeded;
    }

    private boolean InputNeeded = false;
    private String titleState = "none";
    private static BotStage[] stages;
    private static Map<String,BotStage> mapStages;
    BotStage() {}
    BotStage(boolean InputNeeded){
        this.InputNeeded = InputNeeded;
    }
    BotStage(String titleState){
        this.titleState = titleState;
    }
    BotStage(boolean InputNeeded, String titileState) {
        this.InputNeeded = InputNeeded;
        this.titleState = titileState;
    }
    public String getTitleState(){
        return this.titleState;
    }
    public static BotStage getByTitle(String titleState){
        if(mapStages == null){
            mapStages = new HashMap<>();
            Arrays.stream(getStages()).forEach(i -> {
               if(!i.titleState.equals("none")){ mapStages.put(i.titleState,i);}
                System.out.println(i.titleState);
            });
        }
        return mapStages.getOrDefault(titleState,null);
    }
    public static BotStage[] getStages() {
        if(stages == null){
            stages = BotStage.values();
        }
        return stages;
    }

    public BotStage getById(int id){
        return getStages()[id];
    }

    public void SendMessageToChat(BotContext botContext, String text)  {
        try {
            botContext.getBot().execute(new SendMessage(botContext.getClient().getChatId().toString(),text));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
    public abstract void onEntry(BotContext botContext );
    public abstract BotStage onHandleInput(BotContext botContext);
    public abstract BotStage nextBotStage(BotContext botContext);


}
