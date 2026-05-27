package com.spotter_proyect.spotter.core.useCases.client.likeVideo.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.in.LikeVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/video")
@Tag(name = "Cliente", description = "Operaciones del rol CLIENT")
public class LikeVideoController {

    private final LikeVideoUseCase likeVideoUseCase;

    @PostMapping("/like/{id}")
    @Operation(summary = "Dar / quitar like a un vídeo")
    public ResponseEntity<String> likeVideo(@PathVariable Long id){
        return ResponseEntity.ok(likeVideoUseCase.likeVideo(id));
    }

    @GetMapping("/liked")
    @Operation(summary = "Obtener los vídeos a los que el usuario logueado ha dado like")
    public ResponseEntity<List<VideoResponse>> getLikedVideos(){
        return ResponseEntity.ok(likeVideoUseCase.getLikedVideos());
    }
}
