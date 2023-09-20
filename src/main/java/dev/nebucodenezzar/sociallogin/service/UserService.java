package dev.nebucodenezzar.sociallogin.service;

import dev.nebucodenezzar.sociallogin.Entities.Provider;
import dev.nebucodenezzar.sociallogin.Entities.User;
import dev.nebucodenezzar.sociallogin.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;

    public void processOAuthPostLogin(String username) {
        User existUser = repo.getUserByUsername(username);

        if (existUser == null) {
            User newUser = new User();
            newUser.setUsername(username);
//            newUser.setPassword();
            newUser.setProvider(Provider.GOOGLE);
            newUser.setEnabled(1);
            repo.save(newUser);
        }

    }
}
