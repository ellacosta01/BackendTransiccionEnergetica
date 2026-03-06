package com.energy.demo.controller;

import com.energy.demo.controller.dto.LoginRequest;
import com.energy.demo.controller.dto.LoginResponse;
import com.energy.demo.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
// Autenticacion de usuarios y generación de token JWT.
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}