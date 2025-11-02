package com.example.coffee_journal_backend.controllers;

import com.example.coffee_journal_backend.data.CoffeeShopData;
import com.example.coffee_journal_backend.models.CoffeeShop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
