package com.spotter_proyect.spotter.core.useCases.client.likeVideo.application;

import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.in.LikeVideoUseCase;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.domain.LikeVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeVideoOrquet implements LikeVideoUseCase {

    private final LikeVideoService service;

    @Override
    public String likeVideo(Long id){
        return service.deleteVideo(id);
    }
}
