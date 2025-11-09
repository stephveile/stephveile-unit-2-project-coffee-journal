package com.example.coffee_journal_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @NotBlank(message="Coffee shop is required.")
    @JsonManagedReference
    private CoffeeShop coffeeShop;

    @NotBlank(message="Drink order is required.")
    @Size(min=5, max=50, message="Drink order must be 5-50 characters long.")
    private String drinkOrder;

    @NotBlank(message="Rating is required.")
    private int rating;

    @NotBlank(message="Review is required.")
    @Size(max=100, message="Name must be 100 characters or less.")
    private String review;

    @NotBlank(message="Recommendation choice is required.")
    private boolean wouldRecommend;

    @NotBlank(message="Visit date is required.")
    @Size(min=10, max=10, message="Visit date must be in MM/DD/YYYY format.")
    private String visitDate;

    public Entry() {};

    public Entry(CoffeeShop coffeeShop, String drinkOrder, int rating, String review, boolean wouldRecommend, String visitDate) {
        this.coffeeShop = coffeeShop;
        this.drinkOrder = drinkOrder;
        this.rating = rating;
        this.review = review;
        this.wouldRecommend = wouldRecommend;
        this.visitDate = visitDate;
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

    @Override
    public String toString() {
        return "New Journal Entry: " + '\n' +

    }
}
