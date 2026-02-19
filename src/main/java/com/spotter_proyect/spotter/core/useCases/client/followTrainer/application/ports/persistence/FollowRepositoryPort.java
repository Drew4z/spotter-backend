package com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.persistence;

public interface FollowRepositoryPort {
    String followTrainer(Long clientId, Long trainerId);
}
