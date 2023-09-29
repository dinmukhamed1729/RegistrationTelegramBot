package din.registrationtelegrambot.services;


import din.registrationtelegrambot.entities.Client;
import din.registrationtelegrambot.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepositories userRepositories;
    public Client getUserById(Long id){
        return userRepositories.getByChatId(id);
    }
    public void save(Client client){
        userRepositories.save(client);
    }

}
