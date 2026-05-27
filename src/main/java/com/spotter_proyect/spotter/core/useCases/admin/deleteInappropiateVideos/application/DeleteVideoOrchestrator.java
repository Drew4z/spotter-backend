package com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application;

import com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.application.in.DeleteVideoUseCase;
import com.spotter_proyect.spotter.core.useCases.admin.deleteInappropiateVideos.domain.DeleteVideoAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteVideoOrchestrator implements DeleteVideoUseCase {

    private final DeleteVideoAdminService service;

    @Override
    public String deleteVideo(Long videoId) {
        return service.deleteVideo(videoId);
    }
}
