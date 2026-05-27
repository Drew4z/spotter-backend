package com.spotter_proyect.spotter.core.useCases.client.likeVideo.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.LikeVideoEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.LikeVideoRepository;
import com.spotter_proyect.spotter.core.shared.repositories.VideoRepository;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.persistence.LikeVideoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikeVideoRepositoryAdapter implements LikeVideoRepositoryPort {

    private final LikeVideoRepository likeVideoRepository;
    private final VideoRepository videoRepository;
    private final Mapper mapper;

    @Override
    public String deleteLike(Optional<LikeVideoEntity> existingLike, VideoEntity video) {

        likeVideoRepository.delete(existingLike.get());
        video.setLikesCount(video.getLikesCount() - 1);
        videoRepository.save(video);

        return "Se ha eliminado correctamente el like";
    }

    @Override
    public String saveLike(VideoEntity video, UserEntity user) {

        LikeVideoEntity newLike = new LikeVideoEntity();
            newLike.setVideo(video);
            newLike.setUser(user);
        likeVideoRepository.save(newLike);

        video.setLikesCount(video.getLikesCount() + 1);
        videoRepository.save(video);

        return "Se ha guardado correctamente el like";
    }
}
