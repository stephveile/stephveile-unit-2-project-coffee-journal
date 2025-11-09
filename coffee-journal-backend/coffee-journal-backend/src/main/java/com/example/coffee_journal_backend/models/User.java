package com.example.coffee_journal_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    public User() {};

    public User(String userName, String userEmail, String userPassword, String userCity) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userCity = userCity;
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Hi, " + userName + "!" + '\n' +
                "Welcome to your Coffee Journal." + '\n' +
                "Log in to get started!";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
