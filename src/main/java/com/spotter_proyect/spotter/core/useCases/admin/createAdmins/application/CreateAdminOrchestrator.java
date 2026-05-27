package com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application;

import com.spotter_proyect.spotter.core.shared.DTO.CreateAdminRequest;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.createAdmins.application.in.CreateAdminUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.createAdmins.domain.CreateAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAdminOrchestrator implements CreateAdminUseCase {

    private final CreateAdminService service;

    @Override
    public UserResponse createAdmin(CreateAdminRequest request) {
        return service.createAdmin(request);
    }
}
