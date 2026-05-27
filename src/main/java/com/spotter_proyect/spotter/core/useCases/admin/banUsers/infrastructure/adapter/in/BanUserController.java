package com.spotter_proyect.spotter.core.useCases.admin.banUsers.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.admin.banUsers.application.in.BanUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class BanUserController {

    private final BanUserUseCase useCase;

    @PutMapping("/ban/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Banear / desbanear usuario")
    public ResponseEntity<String> banUser(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.toggleBan(id));
    }
}
