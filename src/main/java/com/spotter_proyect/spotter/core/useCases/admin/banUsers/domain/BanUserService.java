package com.spotter_proyect.spotter.core.useCases.admin.banUsers.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.useCases.admin.banUsers.application.persistence.BanUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanUserService {

    private final BanUserRepositoryPort repositoryPort;

    public String toggleBan(Long userId) {
        // Find user by ID
        UserEntity user = repositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario"));

        // Toggle ban status
        user.setIsBanned(!user.getIsBanned());

        // Save updated user
        repositoryPort.save(user);

        // Return confirmation message in Spanish
        if (user.getIsBanned()) {
            return "El usuario ha sido baneado exitosamente";
        } else {
            return "El usuario ha sido desbaneado exitosamente";
        }
    }
}
