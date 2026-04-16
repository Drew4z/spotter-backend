package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.persistence.GetVideosRepositoryPort;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
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

        List<VideoEntity> videos = repository.findAllByTrainerEntityIdOrderByCreatedAtDesc(id);

        return mapper.listVideosEntityToReponse(videos);

    }
}
