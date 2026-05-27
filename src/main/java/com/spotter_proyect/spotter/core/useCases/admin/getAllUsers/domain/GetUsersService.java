package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.domain;


import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.persistence.GetUsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUsersService {

    private final GetUsersRepositoryPort repoPort;

    public List<UserResponse> getUsers(){
        return repoPort.getUsers();
    }
}
