package com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

public interface ModifyVideoRepositoryPort {
    VideoResponse modifyVideo(VideoEntity videoEntity);
}
