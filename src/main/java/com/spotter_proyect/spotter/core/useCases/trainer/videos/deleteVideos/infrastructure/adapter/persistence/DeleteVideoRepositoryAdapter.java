package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.application.port.persistence.DeleteVideoRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
