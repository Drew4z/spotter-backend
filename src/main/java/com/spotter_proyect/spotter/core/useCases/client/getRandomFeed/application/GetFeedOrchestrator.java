package com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.application;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.application.port.in.GetFeedUseCase;
import com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.domain.GetFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFeedOrchestrator implements GetFeedUseCase {

    private final GetFeedService service;

    @Override
    public List<VideoResponse> getFeed(int limit) {
        return service.getFeed(limit);
    }
}
