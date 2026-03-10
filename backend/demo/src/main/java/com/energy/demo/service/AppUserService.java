package com.energy.demo.service;

import com.energy.demo.controller.dto.CreateUserRequest;
import com.energy.demo.model.AppUser;
import com.energy.demo.model.Role;
import com.energy.demo.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> getAll() {
        return appUserRepository.findAll();
    }

    public AppUser getById(Long id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    public AppUser create(CreateUserRequest request) {
        AppUser user = new AppUser();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.valueOf(request.getRole().toUpperCase()));

        return appUserRepository.save(user);
    }

    public AppUser update(Long id, AppUser request) {
        AppUser existing = getById(id);

        existing.setFullName(request.getFullName());
        existing.setEmail(request.getEmail());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            existing.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        existing.setRole(request.getRole());

        return appUserRepository.save(existing);
    }

    public void delete(Long id) {
        AppUser existing = getById(id);
        appUserRepository.delete(existing);
    }
}