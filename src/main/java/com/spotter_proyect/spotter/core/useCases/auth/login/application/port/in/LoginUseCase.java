package com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in;

import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponse;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequest;

public interface LoginUseCase {
    LoginResponse login(LoginRequest request);
}
