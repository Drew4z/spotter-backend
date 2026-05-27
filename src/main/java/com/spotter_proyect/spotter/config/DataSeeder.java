package com.spotter_proyect.spotter.config;


import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Comprobamos si ya existe el admin para no duplicarlo
        if (userRepository.findByEmail("admin@spotter.com").isEmpty()) {

            UserEntity admin = new UserEntity();
            admin.setName("Super Admin");
            admin.setEmail("admin@spotter.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña segura
            admin.setRole(Roles.ADMIN); // <--- EL ROL MÁGICO
            admin.setCreatedAt(LocalDateTime.now());
            admin.setIsPremium(true);

            userRepository.save(admin);
            System.out.println("🚀 ¡Usuario ADMIN creado automáticamente!");
        }
    }
}
