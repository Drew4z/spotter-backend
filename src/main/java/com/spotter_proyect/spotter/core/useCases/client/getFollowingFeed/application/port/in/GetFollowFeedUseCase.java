package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetFollowFeedUseCase {
    Page<VideoResponse> getFeed(int page, int size);
}
