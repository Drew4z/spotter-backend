package com.spotter_proyect.spotter.core.useCases.client.followTrainer.application;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.in.FollowUseCase;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.domain.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowOrquest implements FollowUseCase {

    private final FollowService service;

    @Override
    public String followTrainer(Long id) {
        return service.followTrainer(id);
    }

    @Override
    public List<UserResponse> getFollowedTrainers() {
        return service.getFollowedTrainers();
    }

    @Override
    public List<UserResponse> getTrainerFollowers() {
        return service.getTrainerFollowers();
    }
}
