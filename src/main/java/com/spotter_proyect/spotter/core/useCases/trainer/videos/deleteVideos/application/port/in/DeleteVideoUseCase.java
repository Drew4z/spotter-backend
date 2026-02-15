package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.application.port.in;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;

import java.util.List;

public interface DeleteVideoUseCase {
    String deleteVideo(Long id);
}
