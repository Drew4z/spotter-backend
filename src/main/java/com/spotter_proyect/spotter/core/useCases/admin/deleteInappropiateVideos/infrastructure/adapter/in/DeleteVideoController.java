package com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application.in.DeleteVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("adminDeleteVideoController")
@RequestMapping("/admin/videos")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Operaciones del rol ADMIN")
public class DeleteVideoController {

    private final DeleteVideoUseCase useCase;

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Eliminar vídeo inapropiado")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.deleteVideo(id));
    }
}
