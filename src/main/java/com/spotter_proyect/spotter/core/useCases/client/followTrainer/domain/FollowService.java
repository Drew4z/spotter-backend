package com.spotter_proyect.spotter.core.useCases.client.followTrainer.domain;

import com.spotter_proyect.spotter.core.exceptions.errors.ResourceNotFoundException;
import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.FollowEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.ClientRepository;
import com.spotter_proyect.spotter.core.shared.repositories.FollowRepository;
import com.spotter_proyect.spotter.core.shared.repositories.TrainerRepository;
import com.spotter_proyect.spotter.core.shared.repositories.UserRepository;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.persistence.FollowRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepositoryPort repositoryPort;
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ClientRepository clientRepository;
    private final TrainerRepository trainerRepository;
    private final Mapper mapper;

    public String followTrainer(Long trainerId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = auth.getName();

        ClientEntity clientEntity = clientRepository.findByEmail(emailUser)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al cliente"));

        TrainerEntity trainerEntity = trainerRepository.findById(trainerId)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al entrenador"));

        Optional<FollowEntity> existingFollow = followRepository.findByClientEntityAndTrainerEntity(clientEntity, trainerEntity);

        if (existingFollow.isPresent()) {
            followRepository.delete(existingFollow.get());
            return "Has dejado de seguir a este entrenador.";
        }

        return repositoryPort.followTrainer(clientEntity, trainerEntity);
    }

    public List<UserResponse> getFollowedTrainers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = auth.getName();

        ClientEntity clientEntity = clientRepository.findByEmail(emailUser)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al cliente"));

        List<FollowEntity> follows = followRepository.findByClientEntity(clientEntity);

        return follows.stream()
                .map(follow -> mapper.UserEntityToResponse(follow.getTrainerEntity()))
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of clients following the currently authenticated trainer.
     */
    public List<UserResponse> getTrainerFollowers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUser = auth.getName();

        TrainerEntity trainerEntity = trainerRepository.findByEmail(emailUser)
                .orElseThrow(() -> new ResourceNotFoundException("No se ha encontrado al entrenador"));

        List<FollowEntity> follows = followRepository.findByTrainerEntity(trainerEntity);

        return follows.stream()
                .map(follow -> mapper.UserEntityToResponse(follow.getClientEntity()))
                .collect(Collectors.toList());
    }
}
