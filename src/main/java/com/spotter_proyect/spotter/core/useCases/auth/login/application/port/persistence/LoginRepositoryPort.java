package com.spotter_proyect.spotter.core.useCases.auth.login.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequestDTO;

public interface LoginRepositoryPort {
    UserEntity verifyUser(LoginRequestDTO dto);
}
