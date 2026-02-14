package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO;

public record RegisterRequestDTO(
        String name,
        String email,
        String password,
        String role,
        String specialty,
        String goals
) {
}
