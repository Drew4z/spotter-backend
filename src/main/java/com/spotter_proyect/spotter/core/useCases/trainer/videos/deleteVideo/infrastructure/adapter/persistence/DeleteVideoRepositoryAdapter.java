package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application.port.persistence.DeleteVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteVideoRepositoryAdapter implements DeleteVideoRepositoryPort {

    private final VideoRepository repository;
    private final Mapper mapper;

    @Override
    public String deleteVideo(VideoEntity video){

        repository.delete(video);

        return "Se ha eliminado correctamente el video";
    }
}
