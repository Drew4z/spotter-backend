package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application.ports.in;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;

public interface UploadUseCase {
    VideoResponse upload(VideoRequest request);
}
