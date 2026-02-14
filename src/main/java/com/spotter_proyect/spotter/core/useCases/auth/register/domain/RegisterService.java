package com.spotter_proyect.spotter.core.useCases.auth.register.domain;

import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.persistence.RegisterRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final RegisterRepositoryPort repositoryPort;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(RegisterRepositoryPort repositoryPort, Mapper mapper, PasswordEncoder passwordEncoder) {
        this.repositoryPort = repositoryPort;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequestDTO request) {
        // 1. Encriptamos la contraseña antes de crear el dominio
        RegisterRequestDTO securedRequest = new RegisterRequestDTO(
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.role(),
                request.specialty(),
                request.goals()
        );

        User userToSave = mapper.registerReqToDomain(securedRequest);

        // 2. Guardamos (el puerto se encarga de traducir a Entity)
        return repositoryPort.save(userToSave);
    }
}
