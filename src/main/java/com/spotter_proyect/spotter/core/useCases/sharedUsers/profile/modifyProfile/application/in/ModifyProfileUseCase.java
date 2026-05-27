package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.ModifyProfileRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

public interface ModifyProfileUseCase {

    UserResponse modifyProfile(UserEntity user, ModifyProfileRequest request);
}
