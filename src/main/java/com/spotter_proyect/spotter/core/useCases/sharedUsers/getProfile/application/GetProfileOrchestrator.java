package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.in.GetProfileUseCase;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.domain.GetProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProfileOrchestrator implements GetProfileUseCase {

    private final GetProfileService service;

    @Override
    public UserResponse getProfile(Long userId) {
        return service.getProfile(userId);
    }
}
