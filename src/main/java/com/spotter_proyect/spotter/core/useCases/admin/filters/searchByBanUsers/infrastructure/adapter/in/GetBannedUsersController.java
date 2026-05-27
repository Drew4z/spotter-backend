package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.in.GetBannedUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("adminGetBannedUsersController")
@RequestMapping("/admin/users")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class GetBannedUsersController {

    private final GetBannedUsersUseCase useCase;

    @GetMapping("/banned")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Listar usuarios baneados")
    public ResponseEntity<List<UserResponse>> getBannedUsers() {
        return ResponseEntity.ok(useCase.getBannedUsers());
    }
}
