package com.example.coffee_journal_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonManagedReference
    private CoffeeShop coffeeShop;

    private String drinkOrder;

    private int rating;

    private String review;

    private boolean wouldRecommend;

    private String visitDate;

    @ManyToOne
    @JsonManagedReference
    private User user;

    public Entry(String drinkOrder, int rating, String review, boolean wouldRecommend, String visitDate) {};

    public Entry(CoffeeShop coffeeShop, String drinkOrder, int rating, String review, boolean wouldRecommend, String visitDate, User user) {
        this.coffeeShop = coffeeShop;
        this.drinkOrder = drinkOrder;
        this.rating = rating;
        this.review = review;
        this.wouldRecommend = wouldRecommend;
        this.visitDate = visitDate;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public CoffeeShop getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShop(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    public String getDrinkOrder() {
        return drinkOrder;
    }

    public void setDrinkOrder(String drinkOrder) {
        this.drinkOrder = drinkOrder;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public boolean isWouldRecommend() {
        return wouldRecommend;
    }

    public void setWouldRecommend(boolean wouldRecommend) {
        this.wouldRecommend = wouldRecommend;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "New Journal Entry: " + '\n' +
                "Coffee Shop: " + coffeeShop.getShopName() + '\n' +
                "Drink Order: " + drinkOrder + '\n' +
                "Rating: " + rating + '\n' +
                "Review: " + review;
    }
}
