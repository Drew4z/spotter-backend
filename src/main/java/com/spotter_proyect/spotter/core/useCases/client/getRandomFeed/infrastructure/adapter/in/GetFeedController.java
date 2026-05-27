package com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.application.port.in.GetFeedUseCase;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/video")
@Tag(name = "Cliente", description = "Operaciones del rol CLIENT")
public class GetFeedController {

    private final GetFeedUseCase useCase;

    @GetMapping("/getFeed")
    @Operation(summary = "Feed aleatorio de vídeos", description = "Devuelve N vídeos aleatorios (default 5)")
    public ResponseEntity<List<VideoResponse>> getFeed(@RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(useCase.getFeed(limit));
    }
}
