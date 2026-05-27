package com.spotter_proyect.spotter.core.useCases.admin.banUsers.application;

import com.spotter_proyect.spotter.core.useCases.admin.banUsers.application.in.BanUserUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.banUsers.domain.BanUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BanUserOrchestrator implements BanUserUseCase {

    private final BanUserService service;

    @Override
    public String toggleBan(Long userId) {
        return service.toggleBan(userId);
    }
}
