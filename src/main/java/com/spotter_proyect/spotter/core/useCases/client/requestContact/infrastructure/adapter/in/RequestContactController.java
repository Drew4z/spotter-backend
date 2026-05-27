package com.spotter_proyect.spotter.core.useCases.client.requestContact.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.ContactRequestRequest;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.utils.Utils;
import com.spotter_proyect.spotter.core.useCases.client.requestContact.application.in.RequestContactUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "Cliente", description = "Operaciones del rol CLIENT")
public class RequestContactController {

    private final RequestContactUseCase requestContactUseCase;
    private final Utils utils;

    @PostMapping("/contact/request/{trainerId}")
    @Operation(summary = "Enviar solicitud de contacto a un entrenador")
    public ResponseEntity<String> requestContact(
            @PathVariable Long trainerId,
            @Valid @RequestBody ContactRequestRequest request,
            Authentication authentication) {
        ClientEntity client = (ClientEntity) utils.getUser(authentication);
        return ResponseEntity.ok(requestContactUseCase.requestContact(client, trainerId, request));
    }
}
