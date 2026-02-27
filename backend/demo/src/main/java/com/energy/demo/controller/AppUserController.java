package com.energy.demo.controller;

import com.energy.demo.controller.dto.CreateUserRequest;
import com.energy.demo.controller.dto.UpdateUserRequest;
import com.energy.demo.controller.dto.UserResponse;
import com.energy.demo.service.AppUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
// Agrupa todos los endpoints de usuarios en Swagger bajo este nombre
@Tag(name = "Controlador de Usuarios", description = "Operaciones para gestionar el registro y perfiles de usuarios")
public class AppUserController {

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    // Registra un nuevo usuario en el sistema
    @Operation(summary = "Crear nuevo usuario", description = "Registra un usuario y encripta su contraseña antes de guardarla")
    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.create(request));
    }

    // Obtiene la lista de todos los usuarios registrados
    @Operation(summary = "Listar usuarios", description = "Retorna una lista de todos los usuarios (sin mostrar sus contraseñas)")
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // Busca un usuario específico por su ID
    @Operation(summary = "Buscar por ID", description = "Obtiene los detalles de un usuario específico mediante su ID único")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // Actualiza los datos de un usuario existente
    @Operation(summary = "Actualizar usuario", description = "Permite modificar el nombre, email o rol de un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    // Elimina a un usuario de la base de datos
    @Operation(summary = "Eliminar usuario", description = "Borra permanentemente un usuario del sistema por su ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}