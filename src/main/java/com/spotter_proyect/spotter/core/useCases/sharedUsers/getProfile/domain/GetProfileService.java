package com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.entities.UserEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.FollowRepository;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.getProfile.application.persistence.GetProfileRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProfileService {

    private final GetProfileRepositoryPort repositoryPort;
    private final Mapper mapper;
    private final FollowRepository followRepository;

    public UserResponse getProfile(Long userId) {
        UserEntity user = repositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al usuario"));

        Long followersCount = 0L;
        Long followingCount = 0L;

        if (user instanceof TrainerEntity trainer) {
            followersCount = followRepository.countByTrainerEntity(trainer);
        } else if (user instanceof ClientEntity client) {
            followingCount = followRepository.countByClientEntity(client);
        }

        return mapper.UserEntityToResponse(user, followersCount, followingCount);
    }
}
