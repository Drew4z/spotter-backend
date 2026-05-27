package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.in.GetUsersUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.persistence.GetUsersRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.domain.GetUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersOrquest implements GetUsersUseCase {

    private final GetUsersService service;

    @Override
    public List<UserResponse> getUsers(){
        return service.getUsers();

    }

}
