package com.spotter_proyect.spotter.core.useCases.admin.banUsers.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.admin.banUsers.application.persistence.BanUserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BanUserRepositoryAdapter implements BanUserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
