package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

public interface ModifyProfileRepositoryPort {

    UserEntity save(UserEntity user);
}
