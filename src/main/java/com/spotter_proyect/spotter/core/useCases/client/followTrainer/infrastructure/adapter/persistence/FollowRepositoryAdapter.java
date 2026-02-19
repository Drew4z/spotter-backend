package com.spotter_proyect.spotter.core.useCases.client.followTrainer.infrastructure.adapter.persistence;

import com.spotter_proyect.spotter.core.shared.entities.FollowEntity;
import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.shared.repositories.FollowRepository;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.persistence.FollowRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FollowRepositoryAdapter implements FollowRepositoryPort {

    private final FollowRepository repository;
    private final Mapper mapper;

    @Override
    public String followTrainer(Long clientId, Long trainerId){

        FollowEntity newFollow = mapper.fill(clientId, trainerId);
        FollowEntity savedFollow = repository.save(newFollow);

        return "Se ha guardado correctamente tu like";
    }
}
