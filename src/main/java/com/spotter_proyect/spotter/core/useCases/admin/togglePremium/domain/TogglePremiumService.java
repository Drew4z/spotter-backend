package com.spotter_proyect.spotter.core.useCases.admin.togglePremium.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.useCases.admin.togglePremium.application.persistence.TogglePremiumRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogglePremiumService {

    private final TogglePremiumRepositoryPort repositoryPort;

    public String togglePremium(Long userId) {
        // Find user by ID
        UserEntity user = repositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario"));

        // Toggle premium status
        user.setIsPremium(!user.getIsPremium());

        // Save updated user
        repositoryPort.save(user);

        // Return confirmation message in Spanish
        if (user.getIsPremium()) {
            return "Usuario activado como premium";
        } else {
            return "Usuario desactivado como premium";
        }
    }
}
