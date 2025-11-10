package com.example.coffee_journal_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @NotBlank(message="Username is required.")
    @Size(min=10, max=25, message="Username must be 10-25 characters long.")
    private String userName;

    @NotBlank(message="Email is required.")
    @Size(min=7, max=50, message="Email must be 7-50 characters long.")
    private String userEmail;

    @NotBlank(message="Password is required.")
    @Size(min=10, message="Password must be a minimum of 10 characters long.")
    private String userPassword;

    @NotBlank(message="City is required.")
    @Size(min=2, max=20, message="City must be 2-20 characters long.")
    private String userCity;

    public UserDTO(String userName, String userEmail, String userPassword, String userCity) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCity = userCity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }
}
