package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

public interface UploadUseCase {
    VideoResponse upload(VideoRequest request);
}
