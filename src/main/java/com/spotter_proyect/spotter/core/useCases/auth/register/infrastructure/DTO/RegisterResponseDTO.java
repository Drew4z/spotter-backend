package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO;

import com.spotter_proyect.spotter.core.shared.enums.Roles;

public record RegisterResponseDTO(
        Long id,
        String name,
        String email,
        Roles role


) {
}
