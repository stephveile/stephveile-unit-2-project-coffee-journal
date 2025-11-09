package com.example.coffee_journal_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CoffeeShopDTO {

    @NotBlank(message="Name is required.")
    @Size(min=5, max=50, message="Name must be 5-50 characters long.")
    private String shopName;

    @NotBlank(message="Address is required.")
    @Size(min=5, max=100, message="Address must be 5-100 characters long")
    private String shopAddress;

    @NotBlank(message="Phone number is required.")
    @Size(min=12, max=12, message="Phone number must be XXX-XXX-XXXX format")
    private String shopPhone;

    @NotBlank(message="Hours are required.")
    @Size(min=5, max=25, message="Hours must be 5-25 characters long")
    private String shopHours;

    public CoffeeShopDTO(String shopName, String shopAddress, String shopPhone, String shopHours) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.shopPhone = shopPhone;
        this.shopHours = shopHours;
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
}
