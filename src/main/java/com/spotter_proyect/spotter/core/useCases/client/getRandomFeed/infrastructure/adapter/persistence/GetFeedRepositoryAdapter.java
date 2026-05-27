package com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.client.getRandomFeed.application.port.persistence.GetFeedRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetFeedRepositoryAdapter implements GetFeedRepositoryPort {

    private final VideoRepository videoRepository;
    private final Mapper mapper;

    @Override
    public List<VideoResponse> getRandomFeed(int limit){

        List<VideoEntity> list = videoRepository.findRandomVideos(limit);

        return mapper.listVideosEntityToReponse(list);

    }
}
