package com.spotter_proyect.spotter.core.shared.model;

import java.time.LocalDateTime;

public record Client(
        // --- CAMPOS QUE PIDE LA INTERFAZ USER ---
        Long id,
        String name,
        String email,
        String password,
        String role,
        Boolean isPremium,
        LocalDateTime createdAt,

        // --- CAMPOS EXCLUSIVOS DE CLIENT ---
        String goals
)implements User {
}
