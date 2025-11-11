package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.dto.AddRequestDTO;
import com.example.coffee_journal_backend.models.AddRequest;
import com.example.coffee_journal_backend.models.User;
import com.example.coffee_journal_backend.repositories.AddRequestRepository;
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

@RestController
@RequestMapping("/requests")
public class AddRequestController {

    @Autowired
    AddRequestRepository addRequestRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(value="/all", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRequests() {
        List allRequests = (List) addRequestRepository.findAll();
        return new ResponseEntity<>(allRequests, HttpStatus.OK);
    }

    @GetMapping(value="/details/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRequestById(@PathVariable int id) throws NoResourceFoundException {
        AddRequest addRequest = addRequestRepository.findById(id).orElse(null);
        if (addRequest == null) {
            String path = "/requests/details/" + id;
            throw new NoResourceFoundException(HttpMethod.GET, path);
        } else {
            return new ResponseEntity<>(addRequest, HttpStatus.OK);
        }
    }

    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewRequest(@Valid @RequestBody AddRequestDTO requestData) {
        User user = userRepository.findById(requestData.getUserId()).orElse(null);
        AddRequest addRequest = new AddRequest(user, requestData.getNewCity(), requestData.getNewShopName());
        addRequestRepository.save(addRequest);
        return new ResponseEntity<>(addRequest, HttpStatus.CREATED);
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id) throws NoResourceFoundException {
        AddRequest addRequest = addRequestRepository.findById(id).orElse(null);
        if (addRequest == null) {
            String path = "/requests/delete/" + id;
            throw new NoResourceFoundException(HttpMethod.DELETE, path);
        } else {
            addRequestRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
