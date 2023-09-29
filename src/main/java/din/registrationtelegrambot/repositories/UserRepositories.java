package din.registrationtelegrambot.repositories;

import din.registrationtelegrambot.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepositories extends JpaRepository<Client,Long> {
    Client getByChatId(Long id);
}
