package com.spotter_proyect.spotter.core.useCases.auth.login.application;

import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in.LoginUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.login.domain.LoginService;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponseDTO;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginOrchestrator implements LoginUseCase {

    private final LoginService loginService;

    @Override
    public LoginResponseDTO login(LoginRequestDTO request){
        return loginService.login(request);
    }
}
