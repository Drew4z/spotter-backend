package com.spotter_proyect.spotter.core.shared.model;

import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.enums.SpecialityTrainer;

import java.time.LocalDateTime;

// Fíjate: No hay imports de Jakarta/JPA. ¡Es puro!
public record Trainer(
        Long id,
        String name,
        String email,
        String password,
        Roles role,
        Boolean isPremium,
        LocalDateTime createdAt,
        // Campos específicos de Trainer
        SpecialityTrainer specialty,
        String biography,
        String phoneNumber,
        Boolean isVerified
) implements User{}