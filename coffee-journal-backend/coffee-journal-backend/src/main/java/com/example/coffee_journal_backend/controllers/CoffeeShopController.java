package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.data.CoffeeShopData;
import com.example.coffee_journal_backend.models.CoffeeShop;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/coffeeshops")
public class CoffeeShopController {

    // Get all coffee shops
    // GET request to http://localhost:8080/api/coffeeshops
    @GetMapping("")
    public Collection<CoffeeShop> getAllCoffeeShops() {
        return CoffeeShopData.getAll();
    }

    // Retrieve specific coffee shop
    // GET request to http://localhost:8080/api/coffeeshops/details
    @GetMapping("/details/{shopId}")
    public CoffeeShop getCoffeeShopById(@PathVariable int shopId) {
        return CoffeeShopData.getById(shopId);
    }

    // Save new coffee shop
    // Use query parameters
    // POST request to http://localhost:8080/api/coffeeshops/new
    @PostMapping("/add")
    public String addNewCoffeeShop(@RequestParam String shopName, String shopAddress, String shopPhone, String shopHours) {
        CoffeeShop newCoffeeShop = new CoffeeShop(shopName, shopAddress, shopPhone, shopHours);
        CoffeeShopData.addNew(newCoffeeShop);
        return "Coffee Shop added: " + newCoffeeShop;
    }
}
