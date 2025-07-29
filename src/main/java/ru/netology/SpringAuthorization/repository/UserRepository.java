package ru.netology.SpringAuthorization.repository;

import org.springframework.stereotype.Repository;
import ru.netology.SpringAuthorization.model.Authorities;

import java.util.*;

@Repository
public class UserRepository {

    private final Map<String, String> users = new HashMap<>();
    private final Map<String, List<Authorities>> userAuthorities = new HashMap<>();

    public UserRepository() {

        users.put("admin", "admin123");
        users.put("user", "user123");
        users.put("guest", "guest123");


        userAuthorities.put("admin", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
        userAuthorities.put("user", List.of(Authorities.READ, Authorities.WRITE));
        userAuthorities.put("guest", List.of(Authorities.READ));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {

        if (users.containsKey(user) && users.get(user).equals(password)) {
            return userAuthorities.getOrDefault(user, new ArrayList<>());
        }
        return new ArrayList<>();
    }
}