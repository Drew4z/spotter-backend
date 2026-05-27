package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application;

import com.spotter_proyect.spotter.core.shared.DTO.ModifyProfileRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.in.ModifyProfileUseCase;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.domain.ModifyProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyProfileOrchestrator implements ModifyProfileUseCase {

    private final ModifyProfileService service;

    @Override
    public UserResponse modifyProfile(UserEntity user, ModifyProfileRequest request) {
        return service.modifyProfile(user, request);
    }
}
