package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

public interface UploadRepositoryPort {
    VideoResponse save(VideoEntity videoToSave);
}
