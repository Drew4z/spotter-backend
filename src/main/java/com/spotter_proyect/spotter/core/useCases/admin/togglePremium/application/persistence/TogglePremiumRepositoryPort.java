package com.spotter_proyect.spotter.core.useCases.admin.togglePremium.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

import java.util.Optional;

public interface TogglePremiumRepositoryPort {

    Optional<UserEntity> findById(Long id);

    UserEntity save(UserEntity user);
}
