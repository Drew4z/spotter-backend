package com.spotter_proyect.spotter.core.useCases.admin.togglePremium.application;

import com.spotter_proyect.spotter.core.useCases.admin.togglePremium.application.in.TogglePremiumUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.togglePremium.domain.TogglePremiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TogglePremiumOrchestrator implements TogglePremiumUseCase {

    private final TogglePremiumService service;

    @Override
    public String togglePremium(Long userId) {
        return service.togglePremium(userId);
    }
}
