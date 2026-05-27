package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application;

import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.in.UploadUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.domain.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UploadOrchestrator implements UploadUseCase {

    private final UploadService service;

    @Override
    public VideoResponse upload(VideoRequest request){
        return service.upload(request);
    }
}
