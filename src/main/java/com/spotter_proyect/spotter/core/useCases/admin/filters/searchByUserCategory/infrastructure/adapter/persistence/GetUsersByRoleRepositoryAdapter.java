package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.persistence.GetUsersByRoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("adminGetUsersByRoleRepositoryAdapter")
@RequiredArgsConstructor
public class GetUsersByRoleRepositoryAdapter implements GetUsersByRoleRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public List<UserEntity> findByRole(Roles role) {
        return userRepository.findByRole(role);
    }
}
