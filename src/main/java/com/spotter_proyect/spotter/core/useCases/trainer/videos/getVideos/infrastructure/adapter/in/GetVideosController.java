package com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.useCases.trainer.videos.getVideos.application.port.in.GetVideosUseCase;
import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
public class GetVideosController {

    private final GetVideosUseCase getVideosUseCase;

    @GetMapping("/getVideo/{id}")
    public ResponseEntity<List<VideoResponse>> getTrainerVideos(@PathVariable Long id){

        return ResponseEntity.ok(getVideosUseCase.getVideos(id));
    }
}
