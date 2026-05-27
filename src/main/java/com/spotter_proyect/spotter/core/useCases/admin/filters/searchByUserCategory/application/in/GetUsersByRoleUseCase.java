package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import java.util.List;

public interface GetUsersByRoleUseCase {
    List<UserResponse> getUsersByRole(Roles role);
}
