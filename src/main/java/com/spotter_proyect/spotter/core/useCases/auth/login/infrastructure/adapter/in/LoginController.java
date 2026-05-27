package com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.auth.login.application.port.in.LoginUseCase;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginResponseDTO;
import com.spotter_proyect.spotter.core.useCases.auth.login.infrastructure.DTO.LoginRequestDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Auth", description = "Autenticación de usuarios")
public class LoginController {

    private final LoginUseCase login;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Devuelve un JWT válido por 24h")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(login.login(request));
    }
}
