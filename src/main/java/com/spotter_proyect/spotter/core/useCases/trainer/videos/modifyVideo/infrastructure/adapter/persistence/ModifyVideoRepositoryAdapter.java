package com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.application.port.persistence.ModifyVideoRepositoryPort;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModifyVideoRepositoryAdapter implements ModifyVideoRepositoryPort {

    private final VideoRepository repository;
    private final Mapper mapper;

    @Override
    public VideoResponse modifyVideo(VideoEntity entity){

        VideoEntity savedVideo = repository.save(entity);

        return mapper.videoEntityToResponse(entity);

    }
}
