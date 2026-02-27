package com.energy.demo.controller.dto;

//LO que muestro en la respuesta cuando creo o actualizo el usuario.
public class UserResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String passwordHash;
    private String role; 

    public UserResponse(Long userId, String fullName, String email, String passwordHash, String role) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    // Getters
    public Long getUserId() { return userId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPasswordHash() { return passwordHash; }
    public String getRole() { return role; }
}