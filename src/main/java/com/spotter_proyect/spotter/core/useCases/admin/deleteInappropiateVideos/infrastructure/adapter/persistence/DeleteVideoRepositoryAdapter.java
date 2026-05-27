package com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application.persistence.DeleteVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("adminDeleteVideoRepositoryAdapter")
@RequiredArgsConstructor
public class DeleteVideoRepositoryAdapter implements DeleteVideoRepositoryPort {

    private final VideoRepository videoRepository;

    @Override
    public Optional<VideoEntity> findById(Long id) {
        return videoRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        videoRepository.deleteById(id);
    }
}
