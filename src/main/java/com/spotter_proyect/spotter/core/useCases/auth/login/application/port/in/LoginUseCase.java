package com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in;

import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponseDTO;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequestDTO;

public interface LoginUseCase {
    LoginResponseDTO login(LoginRequestDTO request);
}
