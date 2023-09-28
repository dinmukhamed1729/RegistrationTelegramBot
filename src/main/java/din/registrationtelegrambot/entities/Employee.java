package din.registrationtelegrambot.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Employee {
    @Id
    private Long id;
    long position;

}
