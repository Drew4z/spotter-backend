package com.spotter_proyect.spotter.core.shared.model;

import com.spotter_proyect.spotter.core.shared.enums.GoalsClient;
import com.spotter_proyect.spotter.core.shared.enums.Roles;

import java.time.LocalDateTime;

public record Client(
        // --- CAMPOS QUE PIDE LA INTERFAZ USER ---
        Long id,
        String name,
        String email,
        String password,
        Roles role,
        Boolean isPremium,
        LocalDateTime createdAt,

        // --- CAMPOS EXCLUSIVOS DE CLIENT ---
        GoalsClient goals
)implements User {
}
