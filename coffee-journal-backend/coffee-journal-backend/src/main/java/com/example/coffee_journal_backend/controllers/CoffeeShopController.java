package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.models.CoffeeShop;
import com.example.coffee_journal_backend.repositories.CoffeeShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/coffeeshops")
public class CoffeeShopController {

    @Autowired
    CoffeeShopRepository coffeeShopRepository;

    // Get all coffee shops
    // GET request to http://localhost:8080/api/coffeeshops
    @GetMapping("")
    public Collection<CoffeeShop> getAllCoffeeShops() {
        return coffeeShopRepository.findAll();
    }

    // Retrieve specific coffee shop
    // GET request to http://localhost:8080/api/coffeeshops/details
    @GetMapping("/details/{shopId}")
    public CoffeeShop getCoffeeShopById(@PathVariable int shopId) {
        return coffeeShopRepository.findById(shopId).orElse(null);
    }

    // Save new coffee shop
    // Use query parameters
    // POST request to http://localhost:8080/api/coffeeshops/new
    @PostMapping("/add")
    public String addNewCoffeeShop(@RequestParam String shopName, String shopAddress, String shopPhone, String shopHours) {
        CoffeeShop newCoffeeShop = new CoffeeShop(shopName, shopAddress, shopPhone, shopHours);
        coffeeShopRepository.save(newCoffeeShop);
        return "Coffee Shop added: " + newCoffeeShop;
    }
}
