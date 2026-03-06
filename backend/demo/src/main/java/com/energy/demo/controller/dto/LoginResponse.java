package com.energy.demo.controller.dto;

public class LoginResponse {

    private String token;
    private String type;
    private String email;
    private String role;

    public LoginResponse() {}

    public LoginResponse(String token, String type, String email, String role) {
        this.token = token;
        this.type = type;
        this.email = email;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}