package com.spotter_proyect.spotter.core.useCases.auth.login.application;

import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in.LoginUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.login.domain.LoginService;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponse;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginOrquest implements LoginUseCase {

    private final LoginService loginService;

    @Override
    public LoginResponse login(LoginRequest request){
        return loginService.login(request);
    }
}
