package by.samsolutions.spring.service;

import by.samsolutions.spring.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    List<User> list();
}