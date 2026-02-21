package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.Roles;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        Roles role,
        LocalDateTime createAt
) {
}
