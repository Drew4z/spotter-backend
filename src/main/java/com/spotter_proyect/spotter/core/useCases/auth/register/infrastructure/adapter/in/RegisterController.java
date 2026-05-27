package com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.useCases.auth.register.application.port.in.RegisterUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterRequestDTO;
import com.spotter_proyect.spotter.core.useCases.auth.register.infrastructure.DTO.RegisterResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Autenticación de usuarios")
public class RegisterController {

    private final RegisterUseCase registerUseCase;
    private final Mapper mapper;

    @PostMapping("/register")
    @Operation(summary = "Registrar usuario", description = "Crea una cuenta CLIENT o TRAINER")
    public ResponseEntity<RegisterResponseDTO> register(@Valid @RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(registerUseCase.register(request));
    }
}
