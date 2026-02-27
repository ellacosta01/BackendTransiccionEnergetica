package com.energy.demo.controller.dto;

import jakarta.validation.constraints.*;

//Validaciones para la actualizacion del usuario, similar a la creacion pero sin la contraseña que no es actualizable.
public class UpdateUserRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String fullName;

    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @NotBlank(message = "El rol es obligatorio")
    private String role;

    // Getters y Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}