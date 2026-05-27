package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;

import java.util.List;

public interface GetVideosRepositoryPort {
    List<VideoResponse> getVideosById(Long id);
}
