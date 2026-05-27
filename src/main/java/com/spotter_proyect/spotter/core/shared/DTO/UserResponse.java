package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.Roles;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String name,
        String email,
        Roles role,
        String pathAvatar,
        Boolean isPremium,
        Boolean isBanned,
        String biography,
        String specialty,
        String phoneNumber,
        Boolean isVerified,
        Long followersCount,
        Long followingCount,
        LocalDateTime createAt
) {
}
