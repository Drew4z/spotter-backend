package com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO;

import com.spotter_proyect.spotter.core.shared.enums.Roles;

public record LoginResponseDTO(
        String token,
        Long id,
        String email,
        Roles role
) {
}
