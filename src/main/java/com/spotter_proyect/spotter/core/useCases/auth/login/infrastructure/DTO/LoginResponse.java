package com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO;

public record LoginResponse(
        String token,
        Long id,
        String email,
        String role
) {
}
