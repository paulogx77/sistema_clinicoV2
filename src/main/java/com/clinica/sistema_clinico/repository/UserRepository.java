package com.clinica.sistema_clinico.repository;

import com.clinica.sistema_clinico.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Esse método busca um usuário pelo email (SELECT * FROM users WHERE email = ?)
    Optional<User> findByEmail(String email);

}