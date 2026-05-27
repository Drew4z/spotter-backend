package com.spotter_proyect.spotter.core.useCases.trainer.videos.modifyVideo.application.port.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

public interface ModifyVideoUseCase {
    VideoResponse modifyVideo(Long id, VideoRequest request);
}
