package com.spotter_proyect.spotter.core.useCases.auth.login.domain;

import com.spotter_proyect.spotter.config.security.JwtService;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.persistence.LoginRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponseDTO;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequestDTO;
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

    public LoginResponseDTO login(LoginRequestDTO request){

        // Creamos un objeto no autenticado para luego verificarlo con auth manager y ver si esta en la db
        // Si existe el user pasa al siguiente proceso
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        // Verificamos y buscamos al user
        UserEntity user = repositoryPort.verifyUser(request);

        // Obtenemos le token a traves de nuestro service al pasarle el user
        String jwtToken = jwtService.generateToken(user);

        // Retornamos y mapeamos el user y el token al response
        return mapper.loginReqToResponse(jwtToken,user);
    }
}
