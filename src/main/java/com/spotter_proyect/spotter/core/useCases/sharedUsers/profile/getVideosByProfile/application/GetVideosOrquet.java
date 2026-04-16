package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application;

import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.in.GetVideosUseCase;
import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.domain.GetVideosService;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
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
