package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.shared.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideo.application.ports.in.UploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
public class UploadController {

    private final UploadUseCase uploadUseCase;

    @PostMapping("/upload")
    public ResponseEntity<VideoResponse> uploadVideo(@RequestBody VideoRequest request){

        return ResponseEntity.ok(uploadUseCase.upload(request));

    }

}
