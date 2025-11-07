package com.example.coffee_journal_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class CoffeeShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String shopName;
    private String shopAddress;
    private String shopPhone;
    private String shopHours;

    public CoffeeShop() {};

    public CoffeeShop(String shopName, String shopAddress, String shopPhone, String shopHours) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopPhone = shopPhone;
        this.shopHours = shopHours;
    }

    public int getId() {
        return id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
    }

    public String getShopHours() {
        return shopHours;
    }

    public void setShopHours(String shopHours) {
        this.shopHours = shopHours;
    }

    @Override
    public String toString() {
        return shopName + '\n' +
                "Address = " + shopAddress + '\n' +
                "Phone = " + shopPhone + '\n' +
                "Hours = " + shopHours;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeShop coffeeshop = (CoffeeShop) o;
        return id == coffeeshop.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
