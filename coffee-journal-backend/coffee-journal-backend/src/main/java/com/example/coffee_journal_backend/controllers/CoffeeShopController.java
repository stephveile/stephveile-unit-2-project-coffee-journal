package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.models.CoffeeShop;
import com.example.coffee_journal_backend.repositories.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/coffeeshops")
public class CoffeeShopController {

    @Autowired
    CoffeeShopRepository coffeeShopRepository;

    // Retrieve all coffee shops
    // GET request to http://localhost:8080/api/coffeeshops
    @GetMapping("")
    public ResponseEntity<?> getAllCoffeeShops() {
        List<CoffeeShop> allCoffeeShops = coffeeShopRepository.findAll();
        return new ResponseEntity<>(allCoffeeShops, HttpStatus.OK);
    }

    // Retrieve specific coffee shop
    // GET request to http://localhost:8080/api/coffeeshops/details/{shopId}
    @GetMapping(value="/details/{shopId}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCoffeeShopById(@PathVariable int shopId) {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(shopId).orElse(null);
        if (coffeeShop == null) {
            return new ResponseEntity<>("Coffee shop not found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(coffeeShop, HttpStatus.OK);
        }
    }

    // Save new coffee shop
    // POST request to http://localhost:8080/api/coffeeshops/add
    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewCoffeeShop(@RequestBody CoffeeShop coffeeShop) {
        coffeeShopRepository.save(coffeeShop);
        return new ResponseEntity<>(coffeeShop, HttpStatus.CREATED);
    }

    // Delete existing coffee shop
    // DELETE request to http://localhost:8080/api/coffeeshops/delete/{shopId}
    @DeleteMapping(value="/delete/{shopId}")
    public ResponseEntity<?> deleteCoffeeShop(@PathVariable int shopId) {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(shopId).orElse(null);
        if (coffeeShop == null) {
            return new ResponseEntity<>("Coffee shop not found.", HttpStatus.NOT_FOUND);
        } else {
            coffeeShopRepository.deleteById(shopId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
