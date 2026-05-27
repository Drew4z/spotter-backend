package com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.persistence;

import com.spotter_proyect.spotter.core.shared.entities.ClientEntity;
import com.spotter_proyect.spotter.core.shared.entities.TrainerEntity;

public interface FollowRepositoryPort {
    String followTrainer(ClientEntity client, TrainerEntity trainer);
}
