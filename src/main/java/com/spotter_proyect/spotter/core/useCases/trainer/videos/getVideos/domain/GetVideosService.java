package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.domain;


import com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.persistence.GetVideosRepositoryPort;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetVideosService {

    private final GetVideosRepositoryPort repo;

    public List<VideoResponse> getVideos(Long id){
        return repo.getVideosById(id);
    }
}
