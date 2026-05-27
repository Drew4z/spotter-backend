package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetFollowFeedRepositoryPort {
    Page<VideoResponse> getFollowFeed(Long id, Pageable pageable);
}
