package com.spotter_proyect.spotter.core.useCases.client.likeVideo.domain;


import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.shared.entities.LikeVideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.LikeVideoRepository;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.persistence.LikeVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeVideoService {

    private final LikeVideoRepositoryPort repoPort;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final LikeVideoRepository likeVideoRepository;
    private final Mapper mapper;

    public String likeVideo(Long id){

       // 1. Extraer el video que se quiere dar like
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado el video"));

        // 2. Obtener al usuario que quiere dar like
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado el usuario"));

        var existingLike = likeVideoRepository.findByVideoIdAndUserId(id, user.getId());

        if (existingLike.isPresent()) {
            // --- YA DIO LIKE: QUITARLO (DISLIKE) ---
            return repoPort.deleteLike(existingLike, video);
        } else {
            // --- NO DIO LIKE: CREARLO ---
            return repoPort.saveLike(video, user);
        }
    }

    public List<VideoResponse> getLikedVideos() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException("No se ha encontrado el usuario"));

        List<LikeVideoEntity> likes = likeVideoRepository.findByUserId(user.getId());

        return likes.stream()
                .map(like -> mapper.videoEntityToResponse(like.getVideo()))
                .collect(Collectors.toList());
    }
}