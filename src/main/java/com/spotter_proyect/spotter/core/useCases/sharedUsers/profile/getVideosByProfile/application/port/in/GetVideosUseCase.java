package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

import java.util.List;

public interface GetVideosUseCase {
    List<VideoResponse> getVideos(Long id);
}
