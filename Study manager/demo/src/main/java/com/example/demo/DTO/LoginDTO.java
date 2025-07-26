package com.example.demo.DTO;

import jakarta.validation.constraints.*;

public class LoginDTO {
    @NotBlank private String username;
    @NotBlank private String password;

    public LoginDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters & setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //to string

    @Override
    public String toString() {
        return "LoginDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}