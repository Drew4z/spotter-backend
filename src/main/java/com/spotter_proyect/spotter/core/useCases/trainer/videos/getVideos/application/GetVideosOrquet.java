package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.in.GetVideosUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.domain.GetVideosService;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVideosOrquet implements GetVideosUseCase {

    private final GetVideosService service;

    @Override
    public List<VideoResponse> getVideos(Long id){
        return service.getVideos(id);
    }
}
