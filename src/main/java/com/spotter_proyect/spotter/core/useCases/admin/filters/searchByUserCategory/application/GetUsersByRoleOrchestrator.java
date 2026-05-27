package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.enums.Roles;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.application.in.GetUsersByRoleUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByUserCategory.domain.GetUsersByRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUsersByRoleOrchestrator implements GetUsersByRoleUseCase {

    private final GetUsersByRoleService service;
    private final Mapper mapper;

    @Override
    public List<UserResponse> getUsersByRole(Roles role) {
        return mapper.listUserEntityToResponse(service.getUsersByRole(role));
    }
}
