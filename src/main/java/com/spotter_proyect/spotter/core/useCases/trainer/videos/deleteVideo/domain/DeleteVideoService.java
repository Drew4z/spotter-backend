package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.domain;


import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.exceptions.errors.UnauthorizedActionException;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application.port.persistence.DeleteVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVideoService {

    private final DeleteVideoRepositoryPort repo;
    private final VideoRepository videoRepository;

    public String deleteVideo(Long id){

        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado el video"));

        // 2. SEGURIDAD: Verificar que el usuario actual es el dueño
        String currentEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!video.getTrainerEntity().getEmail().equals(currentEmail)) {
            throw new UnauthorizedActionException("No tienes permiso para eliminar este video");
        }
        return repo.deleteVideo(video);
    }
}
