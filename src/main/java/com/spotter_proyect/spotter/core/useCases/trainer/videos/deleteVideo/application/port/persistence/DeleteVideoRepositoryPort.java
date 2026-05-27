package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;

public interface DeleteVideoRepositoryPort {
    String deleteVideo(VideoEntity video);
}
