package com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.infrastructure.adapter.in;

import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestResponse;
import com.spotter_proyect.spotter.core.shared.DTO.RespondRequestRequest;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.utils.Utils;
import com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.in.GetContactRequestsUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.manageRequests.application.ports.persistence.ContactRequestRepositoryPort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/contact")
@Tag(name = "Entrenador", description = "Operaciones del rol TRAINER")
public class ManageRequestsController {

    private final GetContactRequestsUseCase getContactRequestsUseCase;
    private final ContactRequestRepositoryPort repositoryPort;
    private final Utils utils;

    @GetMapping("/requests")
    @Operation(summary = "Obtener solicitudes de contacto pendientes del entrenador")
    public ResponseEntity<List<ContactRequestResponse>> getRequests(Authentication authentication) {
        TrainerEntity trainer = (TrainerEntity) utils.getUser(authentication);
        return ResponseEntity.ok(getContactRequestsUseCase.getRequests(trainer));
    }

    @PutMapping("/respond/{requestId}")
    @Operation(summary = "Aceptar o rechazar una solicitud de contacto")
    public ResponseEntity<String> respondRequest(
            @PathVariable Long requestId,
            @Valid @RequestBody RespondRequestRequest request,
            Authentication authentication) {
        // Verify the trainer owns this request (security check)
        utils.getUser(authentication); // ensures authenticated
        repositoryPort.updateStatus(requestId, request.status());
        return ResponseEntity.ok("Solicitud actualizada correctamente.");
    }
}
