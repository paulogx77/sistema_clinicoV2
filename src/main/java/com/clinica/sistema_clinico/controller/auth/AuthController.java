package com.clinica.sistema_clinico.controller.auth;

import com.clinica.sistema_clinico.dto.auth.AuthResponse;
import com.clinica.sistema_clinico.dto.auth.LoginRequest;
import com.clinica.sistema_clinico.model.User;
import com.clinica.sistema_clinico.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth") // Todas as rotas aqui começam com /api/auth
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // Endpoint: POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(service.register(request));
    }

    // Endpoint: POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}