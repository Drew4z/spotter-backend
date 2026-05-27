package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.persistence.GetProfileRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetProfileRepositoryAdapter implements GetProfileRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }
}
