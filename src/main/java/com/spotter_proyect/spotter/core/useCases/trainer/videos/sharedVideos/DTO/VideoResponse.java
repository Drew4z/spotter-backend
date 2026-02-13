package com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.enums.VideoCategory;

import java.time.LocalDateTime;

public record VideoResponse(
        Long id,
        String title,
        String videoUrl,
        VideoCategory category,
        String trainerName, // Importante: devolvemos el nombre del creador
        Integer likes,
        LocalDateTime createdAt
) {}
