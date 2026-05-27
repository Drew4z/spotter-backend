package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.domain;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.persistence.GetUsersByRoleRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersByRoleService {

    private final GetUsersByRoleRepositoryPort repositoryPort;

    public List<UserEntity> getUsersByRole(Roles role) {
        return repositoryPort.findByRole(role);
    }
}
