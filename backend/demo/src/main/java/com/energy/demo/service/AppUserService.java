package com.energy.demo.service;

import com.energy.demo.controller.dto.CreateUserRequest;
import com.energy.demo.controller.dto.UpdateUserRequest;
import com.energy.demo.controller.dto.UserResponse;
import com.energy.demo.model.AppUser;
import com.energy.demo.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Reglas de negocio - Casi todas las validaciones.
@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse create(CreateUserRequest request) {
        // Verifico si el email ya existe para evitar duplicados
        if (appUserRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya existe");
        }

        AppUser user = new AppUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        // Encripto la contraseña
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        // Asigno el rol formateado usamos rol_
        user.setRoles(new HashSet<>(Set.of(formatRole(request.getRole()))));

        AppUser saved = appUserRepository.save(user);
        return mapToResponse(saved);
    }

    @Transactional
    public UserResponse update(Long id, UpdateUserRequest request) {
        AppUser u = appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Valido que el nuevo email no lo tenga otro usuario
        appUserRepository.findByEmail(request.getEmail()).ifPresent(existingUser -> {
            if (!existingUser.getUserId().equals(id)) {
                throw new RuntimeException("El nuevo email ya está en uso por otro usuario");
            }
        });

        u.setFullName(request.getFullName());
        u.setEmail(request.getEmail());
        u.setRoles(new HashSet<>(Set.of(formatRole(request.getRole()))));

        AppUser updated = appUserRepository.save(u);
        return mapToResponse(updated);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return appUserRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long id) {
        return appUserRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Transactional
    public void delete(Long id) {
        // Si el usuario no existe, lanzamos error
        if (!appUserRepository.existsById(id)) {
            throw new RuntimeException("No se puede borrar: Usuario no encontrado con ID: " + id);
        }
        // Borrado directo (sin preocuparse por el perfil que eliminamos antes)
        appUserRepository.deleteById(id);
    }

    // Asegura que los roles siempre tengan el formato que Spring Security espera
    private String formatRole(String role) {
        if (role == null || role.isEmpty()) return "ROLE_USER";
        String upper = role.toUpperCase();
        return upper.startsWith("ROLE_") ? upper : "ROLE_" + upper;
    }

    // Convierte la Entidad a una Respuesta limpia para el Front
    private UserResponse mapToResponse(AppUser u) {
        String cleanRole = u.getRoles().stream()
                .findFirst()
                .orElse("USER")
                .replace("ROLE_", "");

        return new UserResponse(
                u.getUserId(),
                u.getFullName(),
                u.getEmail(),
                null, // null en el hash por seguridad
                cleanRole
        );
    }
}