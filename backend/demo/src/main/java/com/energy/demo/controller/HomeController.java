package com.energy.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
// Smock test para verificar que el backend está corriendo y responde-Verificacion anda mas en caso de errores
public class HomeController {

    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "status", "ok",
                "service", "BackendTransicionEnergetica",
                "message", "API running"
        );
    }
}