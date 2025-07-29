package ru.netology.SpringAuthorization.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.SpringAuthorization.exception.InvalidCredentials;
import ru.netology.SpringAuthorization.exception.UnauthorizedUser;
import ru.netology.SpringAuthorization.model.Authorities;
import ru.netology.SpringAuthorization.repository.UserRepository;

@Service
public class AuthorizationService {

    @Autowired
    private UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }
}