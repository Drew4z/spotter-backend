package com.spotter_proyect.spotter.core.useCases.auth.login.domain;

import com.spotter_proyect.spotter.config.security.JwtService;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.persistence.LoginRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponse;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final LoginRepositoryPort repositoryPort;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final Mapper mapper;

    public LoginResponse login(LoginRequest request){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserEntity user = repositoryPort.verifyUser(request);
        String jwtToken = jwtService.generateToken(user);

        return mapper.loginReqToResponse(jwtToken,user);
    }
}
