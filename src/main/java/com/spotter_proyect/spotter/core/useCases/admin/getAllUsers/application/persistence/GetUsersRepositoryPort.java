package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;

import java.util.List;

public interface GetUsersRepositoryPort {

    List<UserResponse> getUsers();
}
