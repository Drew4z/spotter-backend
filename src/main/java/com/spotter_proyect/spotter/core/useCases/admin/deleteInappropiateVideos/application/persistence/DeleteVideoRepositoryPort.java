package com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;

import java.util.Optional;

public interface DeleteVideoRepositoryPort {

    Optional<VideoEntity> findById(Long id);

    void deleteById(Long id);
}
