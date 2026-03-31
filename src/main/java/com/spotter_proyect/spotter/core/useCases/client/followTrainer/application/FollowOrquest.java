package com.spotter_proyect.spotter.core.useCases.client.followTrainer.application;

import com.spotter_proyect.spotter.core.useCases.client.followTrainer.application.ports.in.FollowUseCase;
import com.spotter_proyect.spotter.core.useCases.client.followTrainer.domain.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowOrquest implements FollowUseCase {

    private final FollowService service;

    @Override
    public String followTrainer(Long id){
        return service.followTrainer(id);
    }
}
