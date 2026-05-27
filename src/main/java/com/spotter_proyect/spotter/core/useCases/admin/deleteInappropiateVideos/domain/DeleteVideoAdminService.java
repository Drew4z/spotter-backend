package com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application.persistence.DeleteVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVideoAdminService {

    private final DeleteVideoRepositoryPort repositoryPort;

    public String deleteVideo(Long videoId) {
        repositoryPort.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado el vídeo"));

        repositoryPort.deleteById(videoId);
        return "El vídeo ha sido eliminado exitosamente";
    }
}
