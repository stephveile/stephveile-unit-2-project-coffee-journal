package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.dto.CoffeeShopDTO;
import com.example.coffee_journal_backend.models.CoffeeShop;
import com.example.coffee_journal_backend.repositories.CoffeeShopRepository;
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
@RequestMapping("/api/coffeeshops")
public class CoffeeShopController {

    @Autowired
    CoffeeShopRepository coffeeShopRepository;

    // Retrieve all coffee shops
    // GET request to http://localhost:8080/api/coffeeshops
    @GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllCoffeeShops() {
        List<CoffeeShop> allCoffeeShops = coffeeShopRepository.findAll();
        return new ResponseEntity<>(allCoffeeShops, HttpStatus.OK);
    }

    // Retrieve specific coffee shop
    // GET request to http://localhost:8080/api/coffeeshops/details/{id}
    @GetMapping(value="/details/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getCoffeeShopById(@PathVariable int id) throws NoResourceFoundException {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(id).orElse(null);
        if (coffeeShop == null) {
            String path = "/api/coffeeshops/details/" + id;
            throw new NoResourceFoundException(HttpMethod.GET, path);
        } else {
            return new ResponseEntity<>(coffeeShop, HttpStatus.OK);
        }
    }

    // Save new coffee shop
    // POST request to http://localhost:8080/api/coffeeshops/add
    @PostMapping(value="/add", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNewCoffeeShop(@Valid @RequestBody CoffeeShopDTO coffeeShopData) {
        CoffeeShop coffeeShop = new CoffeeShop(coffeeShopData.getShopName(), coffeeShopData.getShopAddress(), coffeeShopData.getShopPhone(), coffeeShopData.getShopHours());
        coffeeShopRepository.save(coffeeShop);
        return new ResponseEntity<>(coffeeShop, HttpStatus.CREATED);
    }

    // Delete existing coffee shop
    // DELETE request to http://localhost:8080/api/coffeeshops/delete/{id}
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> deleteCoffeeShop(@PathVariable int id) throws NoResourceFoundException {
        CoffeeShop coffeeShop = coffeeShopRepository.findById(id).orElse(null);
        if (coffeeShop == null) {
            String path = "/api/coffeeshops/delete/" + id;
            throw new NoResourceFoundException(HttpMethod.DELETE, path);
        } else {
            coffeeShopRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
