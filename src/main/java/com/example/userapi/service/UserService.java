package com.example.userapi.service;

import com.example.userapi.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    void deleteUserById(UUID id);
}
