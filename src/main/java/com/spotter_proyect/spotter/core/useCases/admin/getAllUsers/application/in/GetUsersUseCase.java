package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;

import java.util.List;

public interface GetUsersUseCase {

    List<UserResponse> getUsers();
}
