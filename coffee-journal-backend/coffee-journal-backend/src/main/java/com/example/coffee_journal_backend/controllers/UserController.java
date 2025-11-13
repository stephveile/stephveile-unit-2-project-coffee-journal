package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.dto.UserDTO;
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

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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

    @GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserById(@PathVariable int id) throws NoResourceFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            String path = "/user/" + id;
            throw new NoResourceFoundException(HttpMethod.GET, path);
        } else {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
    }

    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewUser(@Valid @RequestBody UserDTO userdata) {
        User user = new User(userdata.getUserName(), userdata.getUserEmail(), userdata.getUserPassword(), userdata.getUserCity());
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/delete/{Id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws NoResourceFoundException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            String path = "/user/delete/" + id;
            throw new NoResourceFoundException(HttpMethod.DELETE, path);
        } else {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
