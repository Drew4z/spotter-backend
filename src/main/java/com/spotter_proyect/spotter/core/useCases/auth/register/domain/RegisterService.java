package com.spotter_proyect.spotter.core.useCases.auth.register.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.persistence.RegisterRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final RegisterRepositoryPort repositoryPort;
    private final Mapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public RegisterResponseDTO register(RegisterRequestDTO request) {

        // 0. Verificamos que el email no haya sido registrado antes
        if (userRepository.existsByEmail(request.email())) {
            // Si entra aquí, el email YA existe. Detenemos el registro.
            throw new DuplicateActionException("The email is used, change the email and try again");
        }

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
