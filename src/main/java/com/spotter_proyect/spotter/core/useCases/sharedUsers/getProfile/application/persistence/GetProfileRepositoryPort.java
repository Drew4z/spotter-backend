package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

import java.util.Optional;

public interface GetProfileRepositoryPort {

    Optional<UserEntity> findById(Long id);
}
