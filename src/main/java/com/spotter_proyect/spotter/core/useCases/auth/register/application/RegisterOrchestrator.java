package com.spotter_proyect.spotter.core.useCases.auth.register.application;

import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.in.RegisterUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.register.domain.RegisterService;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterOrchestrator implements RegisterUseCase {
    private final RegisterService registerService;

    @Override
    public RegisterResponseDTO register(RegisterRequestDTO requestDTO){
        return registerService.register(requestDTO);
    }
}

// IMPLEMENTAR QUE CUANDO SE REGISTRE SE MANDE UN CORREO DICIENDO QUE SE HA REGISTRADO SATISFACTORIAMENTE
