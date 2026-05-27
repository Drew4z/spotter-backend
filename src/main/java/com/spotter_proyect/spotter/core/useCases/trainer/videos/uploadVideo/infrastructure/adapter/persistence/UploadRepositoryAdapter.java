package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.persistence.UploadRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UploadRepositoryAdapter implements UploadRepositoryPort {

    private final VideoRepository repository;
    private final Mapper mapper;

    @Override
    public VideoResponse save(VideoEntity entity){

        VideoEntity savedVideo = repository.save(entity);

        return mapper.videoEntityToResponse(savedVideo);
    }
}
