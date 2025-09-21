package com.example.userapi.repository;

import com.example.userapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByFirstNameAndLastName(String firstName, String lastName);
    List<User> findByFirstName(String firstName);
    List<User> findByLastName(String lastName);
}