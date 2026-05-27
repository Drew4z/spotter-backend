package com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.domain;


import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.exceptions.errors.UnauthorizedActionException;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.application.port.persistence.ModifyVideoRepositoryPort;
import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyVideoService {

    private final ModifyVideoRepositoryPort repoPort;
    private final VideoRepository videoRepository;
    private final Mapper mapper;

    public VideoResponse modifyVideo(Long id, VideoRequest request){

        // 1. Buscar el video por ID
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado el video")); // Idealmente usa una excepción personalizada

        // 2. SEGURIDAD: Verificar que el usuario actual es el dueño del video
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!video.getTrainerEntity().getEmail().equals(email)) {
            throw new UnauthorizedActionException("No tienes permiso para editar este video");
        }

        // 3. Actualizar el video con datos nuevos
        VideoEntity modifiedVideo = mapper.modifyVideo(request, video);

        return repoPort.modifyVideo(modifiedVideo);
    }
}
