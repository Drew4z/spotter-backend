package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.persistence;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;

import java.util.List;

public interface GetVideosRepositoryPort {
    List<VideoResponse> getVideosById(Long id);
}
