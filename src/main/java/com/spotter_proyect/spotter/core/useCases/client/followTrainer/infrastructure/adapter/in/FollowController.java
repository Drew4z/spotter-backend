package com.spotter_proyect.spotter.core.useCases.client.followTrainer.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.in.FollowUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Tag(name = "Cliente", description = "Operaciones del rol CLIENT")
public class FollowController {

    private final FollowUseCase port;

    @PostMapping("/followTrainer/{id}")
    @Operation(summary = "Seguir / dejar de seguir a un entrenador")
    public ResponseEntity<String> followTrainer(@PathVariable Long id){
        return ResponseEntity.ok(port.followTrainer(id));
    }

    @GetMapping("/following")
    @Operation(summary = "Obtener entrenadores seguidos (favoritos) por el cliente logueado")
    public ResponseEntity<java.util.List<com.spotter_proyect.spotter.core.shared.DTO.UserResponse>> getFollowing(){
        return ResponseEntity.ok(port.getFollowedTrainers());
    }

    @GetMapping("/followers")
    @Operation(summary = "Obtener clientes que siguen al entrenador logueado")
    public ResponseEntity<java.util.List<com.spotter_proyect.spotter.core.shared.DTO.UserResponse>> getFollowers(){
        return ResponseEntity.ok(port.getTrainerFollowers());
    }
}
