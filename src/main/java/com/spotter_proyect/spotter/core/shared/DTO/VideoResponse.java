package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.VideoCategory;

import java.time.LocalDateTime;

public record VideoResponse(
        Long id,
        String title,
        String videoUrl,
        String frontPagePath,
        VideoCategory category,
        String trainerName, // Importante: devolvemos el nombre del creador
        Integer likes,
        LocalDateTime createdAt
) {}
