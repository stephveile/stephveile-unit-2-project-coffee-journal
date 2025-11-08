package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.models.User;
import com.example.coffee_journal_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @PostMapping("/add")
    public String addNewUser(@RequestParam String userName, String userEmail, String userPassword, String userCity) {
        User newUser = new User(userName, userEmail, userPassword, userCity);
        userRepository.save(newUser);
        return "New user added: " + userName;
    }
}
