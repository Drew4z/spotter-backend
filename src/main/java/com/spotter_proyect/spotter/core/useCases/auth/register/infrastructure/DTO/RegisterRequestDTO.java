package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO;

import com.spotter_proyect.spotter.core.shared.enums.GoalsClient;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.enums.SpecialityTrainer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        String name,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String password,
        Roles role,
        SpecialityTrainer specialty,
        GoalsClient goals
) {
}
