package com.spotter_proyect.spotter.core.shared.DTO;

public record ModifyProfileRequest(
    String name,
    String pathAvatar,
    String biography
) {}
