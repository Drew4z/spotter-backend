package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.in.UploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
@Tag(name = "Entrenador", description = "Operaciones del rol TRAINER")
public class UploadController {

    private final UploadUseCase uploadUseCase;

    @PostMapping("/upload")
    @Operation(summary = "Subir un vídeo")
    public ResponseEntity<VideoResponse> uploadVideo(@RequestBody VideoRequest request){
        return ResponseEntity.ok(uploadUseCase.upload(request));
    }
}
