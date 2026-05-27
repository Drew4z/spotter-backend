package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.persistence.GetUsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class GetIUsersController {

    private final GetUsersRepositoryPort repo;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Obtener todos los usuarios")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(repo.getUsers());
    }
}
