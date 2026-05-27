package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.application.port.in.DeleteVideoUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideo.domain.DeleteVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteVideoOrquet implements DeleteVideoUseCase {

    private final DeleteVideoService service;

    @Override
    public String deleteVideo(Long id){
        return service.deleteVideo(id);
    }
}
