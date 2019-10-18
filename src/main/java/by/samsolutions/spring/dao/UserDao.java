package by.samsolutions.spring.dao;

import by.samsolutions.spring.model.User;

import java.util.List;

public interface UserDao {
    void save(User user);
    List<User> list();
}

