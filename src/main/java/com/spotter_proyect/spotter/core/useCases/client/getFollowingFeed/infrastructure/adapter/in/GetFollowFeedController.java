package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.infrastructure.adapter.in;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.in.GetFollowFeedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/video")
@Tag(name = "Cliente", description = "Operaciones del rol CLIENT")
public class GetFollowFeedController {

    private final GetFollowFeedUseCase useCase;

    @GetMapping("/getFollowingFeed")
    @Operation(summary = "Feed paginado de entrenadores seguidos")
    public ResponseEntity<Page<VideoResponse>> getFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(useCase.getFeed(page, size));
    }
}
