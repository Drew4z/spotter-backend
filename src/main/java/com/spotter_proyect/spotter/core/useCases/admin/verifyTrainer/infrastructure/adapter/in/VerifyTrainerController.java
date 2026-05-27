package com.spotter_proyect.spotter.core.useCases.admin.verifyTrainer.infrastructure.adapter.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.verifyTrainer.application.ports.in.VerifyTrainerUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/trainers")
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class VerifyTrainerController {

    private final VerifyTrainerUseCase verifyTrainerUseCase;

    @GetMapping("/pending")
    @Operation(summary = "Obtener entrenadores pendientes de verificación")
    public ResponseEntity<List<UserResponse>> getPendingTrainers() {
        return ResponseEntity.ok(verifyTrainerUseCase.getPendingTrainers());
    }

    @PutMapping("/approve/{trainerId}")
    @Operation(summary = "Aprobar un entrenador")
    public ResponseEntity<String> approveTrainer(@PathVariable Long trainerId) {
        verifyTrainerUseCase.approveTrainer(trainerId);
        return ResponseEntity.ok("Entrenador aprobado correctamente.");
    }

    @PutMapping("/reject/{trainerId}")
    @Operation(summary = "Rechazar un entrenador")
    public ResponseEntity<String> rejectTrainer(@PathVariable Long trainerId) {
        verifyTrainerUseCase.rejectTrainer(trainerId);
        return ResponseEntity.ok("Entrenador rechazado correctamente.");
    }
}
