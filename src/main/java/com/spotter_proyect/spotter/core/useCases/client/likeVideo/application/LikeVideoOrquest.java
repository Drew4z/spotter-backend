package com.spotter_proyect.spotter.core.useCases.client.likeVideo.application;

import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.in.LikeVideoUseCase;
import com.spotter_proyect.spotter.core.useCases.client.likeVideo.domain.LikeVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeVideoOrquest implements LikeVideoUseCase {

    private final LikeVideoService service;

    @Override
    public String likeVideo(Long id){
        return service.likeVideo(id);
    }

    @Override
    public List<VideoResponse> getLikedVideos() {
        return service.getLikedVideos();
    }
}
