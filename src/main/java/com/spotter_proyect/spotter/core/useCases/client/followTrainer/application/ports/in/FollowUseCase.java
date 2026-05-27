package com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.in;

import com.spotter_proyect.spotter.core.shared.DTO.UserResponse;
import java.util.List;

public interface FollowUseCase {
    String followTrainer(Long id);
    List<UserResponse> getFollowedTrainers();
    List<UserResponse> getTrainerFollowers();
}
