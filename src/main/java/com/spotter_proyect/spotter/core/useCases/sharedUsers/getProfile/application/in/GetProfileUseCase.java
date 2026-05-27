package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;

public interface GetProfileUseCase {

    UserResponse getProfile(Long userId);
}
