package com.example.userapi.service;

import com.example.userapi.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUserById(UUID id);
    User getUserById(UUID id);
    User getUserByName(String firstName, String lastName);
    List<User> getUsersByFirstName(String firstName);
    List<User> getUsersByLastName(String lastName);
}
