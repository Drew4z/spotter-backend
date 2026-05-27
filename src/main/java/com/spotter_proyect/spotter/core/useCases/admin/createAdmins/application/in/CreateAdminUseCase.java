package com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application.in;

import com.spotter_proyect.spotter.core.shared.DTO.CreateAdminRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;

public interface CreateAdminUseCase {

    UserResponse createAdmin(CreateAdminRequest request);
}
