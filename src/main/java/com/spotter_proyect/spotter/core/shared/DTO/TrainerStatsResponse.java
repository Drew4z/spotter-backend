package com.spotter_proyect.spotter.core.shared.DTO;

public record TrainerStatsResponse(
    Long totalFollowers,
    Long totalVideos,
    Long totalLikes
) {}
