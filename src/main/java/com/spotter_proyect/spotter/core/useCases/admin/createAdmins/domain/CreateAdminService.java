package com.spotter_proyect.spotter.core.useCases.admin.createAdmins.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.DuplicateActionException;
import com.spotter_proyect.spotter.core.shared.DTO.CreateAdminRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application.persistence.CreateAdminRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAdminService {

    private final CreateAdminRepositoryPort repositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final Mapper mapper;

    public UserResponse createAdmin(CreateAdminRequest request) {
        if (repositoryPort.existsByEmail(request.email())) {
            throw new DuplicateActionException("El email ya está en uso");
        }

        String encodedPassword = passwordEncoder.encode(request.password());
        UserEntity admin = mapper.createAdminRequestToEntity(request, encodedPassword);
        UserEntity saved = repositoryPort.save(admin);

        return mapper.UserEntityToResponse(saved);
    }
}
