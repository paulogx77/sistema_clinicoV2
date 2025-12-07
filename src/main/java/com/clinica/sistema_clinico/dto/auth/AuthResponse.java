package com.clinica.sistema_clinico.dto.auth;

import com.clinica.sistema_clinico.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String name;
    private Role role; // O Frontend vai ler isso para decidir para qual tela levar o usuário!
}