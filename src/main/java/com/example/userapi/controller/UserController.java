package com.example.userapi.controller;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import com.example.userapi.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public UserController(UserService userService, KafkaProducer kafkaProducer) {
        this.userService = userService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        kafkaProducer.sendUserCreatedEvent("User created with ID: " + savedUser.getId());
        return savedUser;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}