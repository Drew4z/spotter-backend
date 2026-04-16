package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.domain;


import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.shared.utils.Utils;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.persistence.UploadRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final UploadRepositoryPort uploadRepositoryPort;
    private final UserRepository userRepository;
    private final Mapper mapper;
    private final Utils utils;

    public VideoResponse upload(VideoRequest request){
        // 1. Obtener el email del usuario logueado desde el Token JWT
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        UserEntity trainer = utils.getUser(auth);

        // 3. Crear la entidad Video
        VideoEntity videoToSave = mapper.uploadVideoRequestToEntity(request, trainer);

        return uploadRepositoryPort.save(videoToSave);
    }
}
