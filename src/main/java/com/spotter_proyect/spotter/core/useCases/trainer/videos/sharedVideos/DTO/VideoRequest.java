package com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.enums.VideoCategory;

public record VideoRequest(
        String title,
        String videoUrl,
        VideoCategory category
) {
}
