package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.Exeptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exeptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.*;

@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final HashSet<User> users = new HashSet<>();

    @GetMapping("/users")
    public HashSet<User> findAll() {

        log.trace("Текущее количество пользователей: {} ", users.size());
        return users;
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {

        if (user.getEmail() != null && !user.getEmail().equals("")) {
            for (User u : users) {
                if (u.getEmail().equals(user.getEmail())) {
                    throw new UserAlreadyExistException();
                }
            }

        } else if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new InvalidEmailException();

        }
        users.add(user);
        return user;
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) throws InvalidEmailException {

        if (user.getEmail() != null && !user.getEmail().equals("")) {
            users.remove(user);
            users.add(user);
            return user;
        } else {
            throw new InvalidEmailException();
        }
    }

}
