package com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;

public interface CreateAdminRepositoryPort {

    boolean existsByEmail(String email);

    UserEntity save(UserEntity user);
}
