package com.spotter_proyect.spotter.core.useCases.admin.createAdmins.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.CreateAdminRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application.in.CreateAdminUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class CreateAdminController {

    private final CreateAdminUseCase useCase;

    @PostMapping("/createAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Crear un nuevo administrador")
    public ResponseEntity<UserResponse> createAdmin(@Valid @RequestBody CreateAdminRequest request) {
        return ResponseEntity.ok(useCase.createAdmin(request));
    }
}
