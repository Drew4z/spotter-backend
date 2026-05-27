package com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.application.port.in.ModifyVideoUseCase;
import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
@Tag(name = "Entrenador", description = "Operaciones del rol TRAINER")
public class ModifyVideosController {

    private final ModifyVideoUseCase modifyVideoUseCase;

    @PutMapping("/modify/{id}")
    @Operation(summary = "Modificar un vídeo propio")
    public ResponseEntity<VideoResponse> modifyVideo(@PathVariable Long id, @RequestBody VideoRequest request){
        return ResponseEntity.ok(modifyVideoUseCase.modifyVideo(id, request));
    }
}
