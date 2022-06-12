package ru.yandex.practicum.catsgram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.Exeptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exeptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public HashSet<User> findAll() {

        //   log.trace("Текущее количество пользователей: {} ", users.size());
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public Optional<User> findById(@PathVariable int userId) {
        return userService.findAll().stream()
                .filter(x -> x.getId() == userId)
                .findFirst();
    }

    @PostMapping(value = "/users")
    public User create(@RequestBody User user) throws UserAlreadyExistException, InvalidEmailException {
        return userService.create(user);
    }

    @PutMapping(value = "/users")
    public User update(@RequestBody User user) throws InvalidEmailException {

        return userService.update(user);
    }

}
