package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.domain;


import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.application.port.persistence.DeleteVideoRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteVideoService {

    private final DeleteVideoRepositoryPort repo;
    private final VideoRepository videoRepository;

    public String deleteVideo(Long id){

        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Video no encontrado"));

        // 2. SEGURIDAD: Verificar que el usuario actual es el dueño
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!video.getTrainer().getEmail().equals(currentEmail)) {
            throw new RuntimeException("No tienes permiso para eliminar este video ⛔");
        }

        return repo.deleteVideo(video);
    }
}
