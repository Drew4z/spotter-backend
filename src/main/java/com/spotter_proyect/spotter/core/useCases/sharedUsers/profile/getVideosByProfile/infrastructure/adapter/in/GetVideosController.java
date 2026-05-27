package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.in.GetVideosUseCase;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile/video")
@Tag(name = "Perfil", description = "Consulta y modificación de perfiles")
public class GetVideosController {

    private final GetVideosUseCase getVideosUseCase;

    @GetMapping("/get/{id}")
    @Operation(summary = "Obtener vídeos de un perfil")
    public ResponseEntity<List<VideoResponse>> getProfileVideos(@PathVariable Long id){
        return ResponseEntity.ok(getVideosUseCase.getVideos(id));
    }
}
