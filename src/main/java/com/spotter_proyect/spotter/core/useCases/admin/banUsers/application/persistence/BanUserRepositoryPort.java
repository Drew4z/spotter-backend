package com.spotter_proyect.spotter.core.useCases.admin.banUsers.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

import java.util.Optional;

public interface BanUserRepositoryPort {
    
    Optional<UserEntity> findById(Long id);
    
    UserEntity save(UserEntity user);
}
