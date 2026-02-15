package com.spotter_proyect.spotter.core.useCases.client.likeVideo.domain;


import com.spotter_proyect.spotter.core.shared.entities.LikeVideoEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.repositories.LikeVideoRepository;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.persistence.LikeVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeVideoService {

    private final LikeVideoRepositoryPort repo;
    private final VideoRepository videoRepository;
    private final UserRepository userRepository;
    private final LikeVideoRepository likeVideoRepository;

    public String likeVideo(Long id){

       // 1. Extraer el video que se quiere dar like
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("NO SE HA ENCONTRADO EL VIDEO"));

        // 2. Obtener al usuario que quiere dar like
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("NO SE HA ENCONTRADO AL USUARIO"));

        var existingLike = likeVideoRepository.findByVideoIdAndUserId(id, user.getId());

        if (existingLike.isPresent()) {
            


            // --- YA DIO LIKE: QUITARLO (DISLIKE) ---
            likeVideoRepository.delete(existingLike.get());
            video.setLikesCount(video.getLikesCount() - 1);
            videoRepository.save(video);
            return "Like eliminado 💔";
        } else {
            // --- NO DIO LIKE: CREARLO ---
            LikeVideoEntity newLike = new LikeVideoEntity();
            newLike.setVideo(video);
            newLike.setUser(user);
            rep.save(newLike);

            video.setLikesCount(video.getLikesCount() + 1);
            videoRepository.save(video);
            return "Like añadido ❤️";

    }
}
