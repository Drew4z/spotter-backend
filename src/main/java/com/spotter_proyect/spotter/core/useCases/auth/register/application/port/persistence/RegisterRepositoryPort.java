package com.spotter_proyect.spotter.core.useCases.auth.register.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;

public interface RegisterRepositoryPort {

    RegisterResponseDTO save(User userDomain);
}
