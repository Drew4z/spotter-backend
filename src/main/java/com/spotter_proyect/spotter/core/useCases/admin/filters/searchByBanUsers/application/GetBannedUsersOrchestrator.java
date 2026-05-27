package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.in.GetBannedUsersUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.domain.GetBannedUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetBannedUsersOrchestrator implements GetBannedUsersUseCase {

    private final GetBannedUsersService service;
    private final Mapper mapper;

    @Override
    public List<UserResponse> getBannedUsers() {
        return mapper.listUserEntityToResponse(service.getBannedUsers());
    }
}
