package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.in.GetProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Tag(name = "Perfil", description = "Consulta y modificación de perfiles")
public class GetProfileController {

    private final GetProfileUseCase useCase;

    @GetMapping("/{id}")
    @Operation(summary = "Obtener perfil por ID")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.getProfile(id));
    }
}
