package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.modifyProfile.application.persistence.ModifyProfileRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ModifyProfileRepositoryAdapter implements ModifyProfileRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
