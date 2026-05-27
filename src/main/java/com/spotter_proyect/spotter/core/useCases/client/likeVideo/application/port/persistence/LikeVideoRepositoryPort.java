package com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.entities.LikeVideoEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;

import java.util.Optional;

public interface LikeVideoRepositoryPort {
    String deleteLike(Optional<LikeVideoEntity> videoEntityOptional, VideoEntity video);

    String saveLike(VideoEntity video, UserEntity user);
}
