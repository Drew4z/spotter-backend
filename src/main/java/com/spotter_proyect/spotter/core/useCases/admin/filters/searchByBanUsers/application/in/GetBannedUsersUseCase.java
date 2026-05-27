package com.spotter_proyect.spotter.core.useCases.admin.filters.searchByBanUsers.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import java.util.List;

public interface GetBannedUsersUseCase {
    List<UserResponse> getBannedUsers();
}
