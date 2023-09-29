package din.registrationtelegrambot.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
public class Client
{
    @Id
    private Long chatId;
    private String  firstName, userName;
    private String titleState;
    public Client(Long chatId, String firstName, String titleState){
        this.chatId = chatId;
        this.firstName = firstName;
        this.titleState = titleState;
    }

    public Client() {

    }
}
