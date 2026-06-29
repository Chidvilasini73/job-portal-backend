package com.jobportal.jobportalbackend.controller;

import com.jobportal.jobportalbackend.entity.User;
import com.jobportal.jobportalbackend.repository.UserRepository;
import com.jobportal.jobportalbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(UserService userService,UserRepository userRepository) {
        this.userService = userService;
        this.userRepository=userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping("/users")
    public User createUser(@RequestBody User user)
    {
        return userService.createUser(user);
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id,@RequestBody User updatedUser)
    {
        return userService.updateUser(id, updatedUser);
    }
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
    @GetMapping("/users/email/{email}")
    public User getUserByEmail(@PathVariable String email)
    {
        return userService.getUserByEmail(email);
    }
    @PostMapping("/users/bulk")
    public List<User> createUsers(@RequestBody List<User> users)
    {
        return userService.createUsers(users);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user)
    {
        return userService.login(
                user.getEmail(),
                user.getPassword()
        );
    }
}