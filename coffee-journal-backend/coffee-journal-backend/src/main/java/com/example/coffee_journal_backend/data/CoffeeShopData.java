package com.example.coffee_journal_backend.data;

import com.example.coffee_journal_backend.models.CoffeeShop;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CoffeeShopData {

    // Temporary data used for initial testing purposes (following one line)
    private static final Map<Integer, CoffeeShop> coffeeshops = new HashMap<>();

    // Temporary CRUD methods for initial testing (following three methods)
    public static Collection<CoffeeShop> getAll() {
        return coffeeshops.values();
    }

    public static void addNew(CoffeeShop coffeeshop) {
        coffeeshops.put(coffeeshop.getId(), coffeeshop);
    }

    public static CoffeeShop getById(int id) {
        return coffeeshops.get(id);
    }

}
