package com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.in;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import java.util.List;

public interface LikeVideoUseCase {
    String likeVideo(Long id);
    List<VideoResponse> getLikedVideos();
}
