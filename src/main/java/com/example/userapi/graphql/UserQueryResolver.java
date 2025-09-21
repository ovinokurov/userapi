package com.example.userapi.graphql;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class UserQueryResolver {

    private final UserService userService;

    public UserQueryResolver(UserService userService) {
        this.userService = userService;
    }

    @QueryMapping
    public List<User> users() {
        return userService.getAllUsers();
    }

    @QueryMapping
    public User user(@Argument UUID id) {
        return userService.getUserById(id);
    }

    @QueryMapping
    public User userByName(@Argument String firstName, @Argument String lastName) {
        return userService.getUserByName(firstName, lastName);
    }

    @QueryMapping
    public List<User> usersByFirstName(@Argument String firstName) {
        return userService.getUsersByFirstName(firstName);
    }

    @QueryMapping
    public List<User> usersByLastName(@Argument String lastName) {
        return userService.getUsersByLastName(lastName);
    }
}