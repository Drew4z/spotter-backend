package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application.port.in.DeleteVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
@Tag(name = "Entrenador", description = "Operaciones del rol TRAINER")
public class DeleteVideoController {

    private final DeleteVideoUseCase deleteVideoUseCase;

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar un vídeo propio")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id){
        return ResponseEntity.ok(deleteVideoUseCase.deleteVideo(id));
    }
}
