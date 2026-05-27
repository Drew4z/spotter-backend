package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.VideoCategory;

import java.time.LocalDateTime;

public record VideoResponse(
        Long id,
        String title,
        String videoUrl,
        String frontPagePath,
        VideoCategory category,
        String trainerName,
        Long trainerId, // ID del entrenador para redirecciones en el frontend
        String trainerAvatar, // Avatar del entrenador para el feed
        Integer likes,
        LocalDateTime createdAt
) {}
