package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.domain;

import com.spotter_proyect.spotter.core.shared.DTO.ModifyProfileRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.persistence.ModifyProfileRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyProfileService {

    private final ModifyProfileRepositoryPort repositoryPort;
    private final Mapper mapper;

    public UserResponse modifyProfile(UserEntity user, ModifyProfileRequest request) {
        if (request.name() != null) {
            user.setName(request.name());
        }
        if (request.pathAvatar() != null) {
            user.setPathAvatar(request.pathAvatar());
        }
        if (request.biography() != null && user instanceof TrainerEntity trainer) {
            trainer.setBiography(request.biography());
        }

        UserEntity updatedUser = repositoryPort.save(user);
        return mapper.UserEntityToResponse(updatedUser);
    }
}
