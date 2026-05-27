package com.spotter_proyect.spotter.core.useCases.admin.togglePremium.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.admin.togglePremium.application.in.TogglePremiumUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class TogglePremiumController {

    private final TogglePremiumUseCase useCase;

    @PutMapping("/premium/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Activar / desactivar premium a un usuario")
    public ResponseEntity<String> togglePremium(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.togglePremium(id));
    }
}
