package main.java.org.example.dao;

import main.java.org.example.model.User;

import java.util.List;

public interface UserDao
{
    List<User> getUsers();

    User getUserById(int userId);

    User getUserByUsername(String username);

    User createUser(User newUser);

    User updateUser(User newUser);
}
