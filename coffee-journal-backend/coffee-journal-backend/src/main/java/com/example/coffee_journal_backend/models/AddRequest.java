package com.example.coffee_journal_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class AddRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonManagedReference
    private User user;

    private String newCity;

    private String newShopName;

    public AddRequest(String newCity, String newShopName) {};

    public AddRequest(User user, String newCity, String newShopName) {
        this.user = user;
        this.newCity = newCity;
        this.newShopName = newShopName;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Request to add new city or coffee shop!";
    }
}
