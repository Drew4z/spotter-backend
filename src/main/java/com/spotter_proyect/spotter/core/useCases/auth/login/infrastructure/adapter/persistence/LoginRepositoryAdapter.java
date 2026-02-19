package com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.exceptions.GlobalExceptionHandler;
import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.persistence.LoginRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginRepositoryAdapter implements LoginRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public UserEntity verifyUser(LoginRequest request){
        return userRepository.findByEmail(request.email())
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado al usuario"));

    }
}
