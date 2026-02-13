package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application.ports.in.UploadUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.domain.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadOrquest implements UploadUseCase {

    private final UploadService service;

    @Override
    public VideoResponse upload(VideoRequest request){
        return service.upload(request);
    }
}
