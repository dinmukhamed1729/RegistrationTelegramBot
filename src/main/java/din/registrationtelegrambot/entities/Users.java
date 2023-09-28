package din.registrationtelegrambot.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Users
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private Long chatId;
    private String lastName, firstName, userName;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private  Employee employee;
}
