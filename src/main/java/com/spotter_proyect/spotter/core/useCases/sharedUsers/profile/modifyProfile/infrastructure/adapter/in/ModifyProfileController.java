package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.ModifyProfileRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.utils.Utils;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.in.ModifyProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
@Tag(name = "Perfil", description = "Consulta y modificación de perfiles")
public class ModifyProfileController {

    private final ModifyProfileUseCase useCase;
    private final Utils utils;

    @PutMapping("/modify")
    @Operation(summary = "Modificar perfil del usuario autenticado")
    public ResponseEntity<UserResponse> modifyProfile(
            @RequestBody ModifyProfileRequest request,
            Authentication authentication) {
        UserEntity user = utils.getUser(authentication);
        return ResponseEntity.ok(useCase.modifyProfile(user, request));
    }
}
