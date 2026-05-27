package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.in.GetFollowFeedUseCase;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.domain.GetFollowFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetFollowFeedOrchestrator implements GetFollowFeedUseCase {

    private final GetFollowFeedService service;

    @Override
    public Page<VideoResponse> getFeed(int page, int size) {
        return service.getFeed(page, size);
    }
}
