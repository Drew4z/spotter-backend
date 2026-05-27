package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import java.util.List;

public interface GetUsersByRoleRepositoryPort {
    List<UserEntity> findByRole(Roles role);
}
