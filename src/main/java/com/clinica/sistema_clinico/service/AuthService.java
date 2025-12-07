package com.clinica.sistema_clinico.service;

import com.clinica.sistema_clinico.dto.auth.AuthResponse;
import com.clinica.sistema_clinico.dto.auth.LoginRequest;
import com.clinica.sistema_clinico.model.User;
import com.clinica.sistema_clinico.model.enums.Role;
import com.clinica.sistema_clinico.repository.UserRepository;
import com.clinica.sistema_clinico.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Método para registrar um novo usuário (ex: o primeiro ADMIN)
    public AuthResponse register(User request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Criptografa a senha
                .role(request.getRole()) // Define o cargo
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .name(user.getName())
                .role(user.getRole())
                .build();
    }

    // Método para autenticar um usuário existente
    public AuthResponse authenticate(LoginRequest request) {
        // O Spring Security valida o email e senha. Se estiver errado, ele joga uma exceção.
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // Se passou, o usuário é válido. Buscamos ele no banco para pegar os dados.
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        // Devolve o token e os dados do usuário para o frontend
        return AuthResponse.builder()
                .token(jwtToken)
                .name(user.getName())
                .role(user.getRole())
                .build();
    }
}