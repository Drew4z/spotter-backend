package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.infrastructure.adapter.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.in.GetFollowFeedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/video")
public class GetFollowFeedController {

    private final GetFollowFeedUseCase useCase;

    @GetMapping("/getFollowFeed")
    public ResponseEntity<Page<VideoResponse>> getFeed(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(useCase.getFeed(page, size));
    }
}
