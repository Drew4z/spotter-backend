package com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.client.getFollowingFeed.application.port.persistence.GetFollowFeedRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetFollowFeedRepositoryAdapter implements GetFollowFeedRepositoryPort {

    private final VideoRepository videoRepository;
    private final Mapper mapper;

    @Override
    public Page<VideoResponse> getFollowFeed(Long id, Pageable pageable){

        Page<VideoEntity> entityPage = videoRepository.findVideosFromFollowedTrainers(id, pageable);
        return mapper.pageEntityToResponse(entityPage);
    }
}
