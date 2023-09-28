package din.registrationtelegrambot.repositories;

import din.registrationtelegrambot.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepositories extends JpaRepository<Users,Long> {
 }
