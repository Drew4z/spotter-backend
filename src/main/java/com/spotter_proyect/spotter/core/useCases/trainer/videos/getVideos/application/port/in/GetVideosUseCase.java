package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.in;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;

import java.util.List;

public interface GetVideosUseCase {
    List<VideoResponse> getVideos(Long id);
}
