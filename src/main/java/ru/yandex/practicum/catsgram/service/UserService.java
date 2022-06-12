package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.Exeptions.InvalidEmailException;
import ru.yandex.practicum.catsgram.Exeptions.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.HashSet;

@Service
public class UserService {


    private final HashSet<User> users = new HashSet<>();

    public HashSet<User> findAll() {
        return users;
    }

    public User create(User user) throws UserAlreadyExistException, InvalidEmailException {
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

    public User update(User user) throws InvalidEmailException {
        if (user.getEmail() != null && !user.getEmail().equals("")) {
            users.remove(user);
            users.add(user);
            return user;
        } else {
            throw new InvalidEmailException();
        }
    }

    public User findUserByEmail(String email) {
        for (User item : users) {
            if (item.getEmail().equals(email)) {
                return item;
            }
        }
        return null;
    }
}
