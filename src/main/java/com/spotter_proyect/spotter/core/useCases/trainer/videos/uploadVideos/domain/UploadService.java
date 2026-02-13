package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.domain;


import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.model.User;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application.ports.persistence.UploadRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.infrastructure.adapter.persistence.UploadRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepositoryPort uploadRepositoryPort;
    private final UserRepository userRepository;
    private final Mapper mapper;

    public VideoResponse upload(VideoRequest request){
        // 1. Obtener el email del usuario logueado desde el Token JWT
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        // 2. Buscar al usuario en la BBDD
        UserEntity trainer = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // 3. Crear la entidad Video
        VideoEntity videoToSave = mapper.uploadVideoRequestToEntity(request, trainer);

        return uploadRepositoryPort.save(videoToSave);
    }
}
