package org.example.service;

import org.example.entity.User;
import java.util.List;

public interface UserService {

    void createTableUsers();

    void addUser(User user);

    void updateUser(User newUser);

    void deleteUser(long id);

    User getUserById(long id);

    List<User> getAllUsers();
}