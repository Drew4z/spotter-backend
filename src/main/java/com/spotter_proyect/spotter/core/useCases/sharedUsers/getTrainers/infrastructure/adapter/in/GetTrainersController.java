package com.spotter_proyect.spotter.core.useCases.sharedUsers.getTrainers.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.TrainerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainers")
@Tag(name = "Entrenadores", description = "Listado de entrenadores — accesible para usuarios autenticados")
public class GetTrainersController {

    private final TrainerRepository trainerRepository;
    private final Mapper mapper;

    /**
     * GET /trainers
     * Returns all trainers. Accessible to any authenticated user.
     */
    @GetMapping
    @Operation(summary = "Obtener todos los entrenadores")
    public ResponseEntity<List<UserResponse>> getAllTrainers() {
        List<UserResponse> trainers = trainerRepository.findAll()
                .stream()
                .map(mapper::UserEntityToResponse)
                .toList();
        return ResponseEntity.ok(trainers);
    }

    /**
     * GET /trainers/search?name=alex
     * Filters trainers by name (case-insensitive, partial match).
     * Accessible to any authenticated user.
     */
    @GetMapping("/search")
    @Operation(summary = "Buscar entrenadores por nombre", description = "Búsqueda parcial e insensible a mayúsculas")
    public ResponseEntity<List<UserResponse>> searchTrainers(@RequestParam String name) {
        List<UserResponse> trainers = trainerRepository
                .findByNameContainingIgnoreCase(name.trim())
                .stream()
                .map(mapper::UserEntityToResponse)
                .toList();
        return ResponseEntity.ok(trainers);
    }
}
