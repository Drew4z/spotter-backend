package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.in.GetUsersByRoleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminGetUsersByRoleController")
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class GetUsersByRoleController {

    private final GetUsersByRoleUseCase useCase;

    @GetMapping("/byRole")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Filtrar usuarios por rol", description = "Valores válidos: CLIENT, TRAINER, ADMIN")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@RequestParam Roles role) {
        return ResponseEntity.ok(useCase.getUsersByRole(role));
    }
}
