package com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.admin.getAllUsers.application.persistence.GetUsersRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetUsersRepositoryAdapter implements GetUsersRepositoryPort {

    private final UserRepository userRepository;
    private final Mapper mapper;

    @Override
    public List<UserResponse> getUsers(){

        List<UserEntity> list = userRepository.findAll();
        return mapper.listUserEntityToResponse(list);
    }
}
