package com.example.userapi.service;

import com.example.userapi.model.User;
import com.example.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private UserService userService;

    // Set up mock UserRepository before each test and inject into the service
    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    /**
     * Unit Test: testGetAllUsers_ReturnsListOfUsers
     * -------------------------------------------------
     * Purpose: To verify that the service correctly retrieves a list of users.
     * - We mock the repository's findAll() method to return a predefined list.
     * - The test then verifies that:
     *     - The returned list size is correct.
     *     - The content of the first and second users is as expected.
     *     - The repository's findAll method was called exactly once.
     */
    @Test
    void testGetAllUsers_ReturnsListOfUsers() {
        List<User> mockUsers = Arrays.asList(
                new User("Oleg", "Vinokurov"),
                new User("Simona", "Vinokurov")
        );

        // Mock behavior: when findAll is called, return our mock list
        when(userRepository.findAll()).thenReturn(mockUsers);

        // Call the actual service method
        List<User> users = userService.getAllUsers();

        // Validate the size and values
        assertEquals(2, users.size());
        assertEquals("Oleg", users.get(0).getFirstName());
        assertEquals("Vinokurov", users.get(1).getLastName());

        // Verify that repository.findAll() was called once
        verify(userRepository, times(1)).findAll();
    }

    /**
     * Unit Test: testCreateUser_SavesAndReturnsUser
     * -------------------------------------------------
     * Purpose: To verify that the service saves a user and returns the correct result.
     * - A new user is passed into the service.
     * - We mock the repository to simulate a "saved" user (with generated ID).
     * - We check:
     *     - The returned object is not null.
     *     - The first name and last name match expectations.
     *     - An ID was assigned.
     *     - The save method was called once with the correct parameter.
     */
    @Test
    void testCreateUser_SavesAndReturnsUser() {
        User newUser = new User("Oleg", "Vinokurov");
        User savedUser = new User("Oleg", "Vinokurov");
        savedUser.setId(UUID.randomUUID()); // Simulate DB assigning ID

        // Mock save behavior: return savedUser when save is called
        when(userRepository.save(newUser)).thenReturn(savedUser);

        // Call the actual service method
        User result = userService.createUser(newUser);

        // Validate result
        assertNotNull(result);
        assertEquals("Oleg", result.getFirstName());
        assertEquals("Vinokurov", result.getLastName());
        assertNotNull(result.getId()); // Should be set by database

        // Verify that repository.save() was called once with newUser
        verify(userRepository, times(1)).save(newUser);
    }

    /**
     * Unit Test: testDeleteUserById_DeletesUserSuccessfully
     * -----------------------------------------------------
     * Purpose: To verify that the service correctly deletes a user when the user exists.
     * - A random UUID is generated to simulate an existing user ID.
     * - We mock the repository's existsById() to return true, simulating that the user exists.
     * - The service is expected to call deleteById() without throwing any exceptions.
     * - The test verifies:
     *     - existsById() is called once with the correct ID.
     *     - deleteById() is called once with the same ID.
     */
    @Test
    void testDeleteUserById_DeletesUserSuccessfully() {
        // Arrange: create a fake user ID
        UUID userId = UUID.randomUUID();

        // Mock behavior: userRepository.existsById(userId) should return true
        when(userRepository.existsById(userId)).thenReturn(true);

        // Act: call the service method to delete the user
        userService.deleteUserById(userId);

        // Assert: verify that existsById and deleteById were both called exactly once
        verify(userRepository, times(1)).existsById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

}