package com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.shared.mapper.Mapper;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoRequest;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.uploadVideos.application.ports.in.UploadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/video")
public class UploadController {

    private final UploadUseCase uploadUseCase;

    @PostMapping("/upload")
    public ResponseEntity<VideoResponse> uploadVideo(@RequestBody VideoRequest request){

        VideoResponse response = uploadUseCase.upload(request);

        return ResponseEntity.ok()
                .body(response);

    }

}
