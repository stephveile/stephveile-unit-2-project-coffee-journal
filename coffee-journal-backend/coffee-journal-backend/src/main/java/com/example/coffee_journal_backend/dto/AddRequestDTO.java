package com.example.coffee_journal_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddRequestDTO {

    @NotNull(message="User is required.")
    private int userId;

    @Size(min=2, max=20, message="City must be 2-20 characters long.")
    private String newCity;

    @Size(min=5, max=50, message="Name must be 5-50 characters long.")
    private String newShopName;

    public AddRequestDTO(int userId, String newCity, String newShopName) {
        this.userId = userId;
        this.newCity = newCity;
        this.newShopName = newShopName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNewCity() {
        return newCity;
    }

    public void setNewCity(String newCity) {
        this.newCity = newCity;
    }

    public String getNewShopName() {
        return newShopName;
    }

    public void setNewShopName(String newShopName) {
        this.newShopName = newShopName;
    }
}
