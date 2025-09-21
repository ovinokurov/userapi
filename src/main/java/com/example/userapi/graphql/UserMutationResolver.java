package com.example.userapi.graphql;

import com.example.userapi.model.User;
import com.example.userapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserMutationResolver {

    @Autowired
    private UserService userService;

    @MutationMapping
    public User createUser(@Argument String firstName, @Argument String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userService.createUser(user);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument UUID id) {
        try {
            userService.deleteUserById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

