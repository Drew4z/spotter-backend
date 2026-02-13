package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application.ports.persistence;

import com.spotter_proyect.spotter.core.shared.entities.VideoEntity;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;

public interface UploadRepositoryPort {

    VideoResponse save(VideoEntity videoToSave);
}
