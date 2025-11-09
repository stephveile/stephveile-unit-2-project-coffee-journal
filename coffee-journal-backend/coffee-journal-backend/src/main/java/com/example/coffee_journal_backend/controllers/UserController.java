package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.models.CoffeeShop;
import com.example.coffee_journal_backend.models.User;
import com.example.coffee_journal_backend.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/all", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(value="/{userId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable int userId) throws NoResourceFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            String path = "/user/" + userId;
            throw new NoResourceFoundException(HttpMethod.GET, path);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@Valid @RequestParam User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) throws NoResourceFoundException {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            String path = "/user/delete/" + userId;
            throw new NoResourceFoundException(HttpMethod.DELETE, path);
        } else {
            userRepository.deleteById(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
