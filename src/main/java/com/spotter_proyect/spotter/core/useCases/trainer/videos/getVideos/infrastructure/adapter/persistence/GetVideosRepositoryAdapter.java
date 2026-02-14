package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.persistence.GetVideosRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetVideosRepositoryAdapter implements GetVideosRepositoryPort {

    private final VideoRepository repository;
    private final Mapper mapper;

    @Override
    public List<VideoResponse> getVideosById(Long id){

        List<VideoEntity> videos = repository.findAllByTrainerIdOrderByCreatedAtDesc(id);

        return mapper.listVideosEntityToReponse(videos);

    }
}
