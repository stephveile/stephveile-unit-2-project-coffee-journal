package com.example.coffee_journal_backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String userEmail;

    private String userPassword;

    private String userCity;

    @OneToMany(mappedBy= "user")
    @JsonBackReference
    private final List<Entry> entries = new ArrayList<>();

    @OneToMany(mappedBy= "user")
    @JsonBackReference
    private final List<AddRequest> addRequests = new ArrayList<>();

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
