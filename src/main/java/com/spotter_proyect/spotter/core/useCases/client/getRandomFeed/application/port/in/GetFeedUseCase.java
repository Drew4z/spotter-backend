package com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.application.port.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

import java.util.List;

public interface GetFeedUseCase {
    List<VideoResponse> getFeed(int limit);
}
