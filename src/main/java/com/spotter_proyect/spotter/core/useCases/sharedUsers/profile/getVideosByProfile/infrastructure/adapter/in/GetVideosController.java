package com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.useCases.sharedUsers.profile.getVideosByProfile.application.port.in.GetVideosUseCase;
import com.spotter_proyect.spotter.core.shared.DTO.VideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile/video")
public class GetVideosController {

    private final GetVideosUseCase getVideosUseCase;

    @GetMapping("/get/{id}")
    public ResponseEntity<List<VideoResponse>> getProfileVideos(@PathVariable Long id){

        return ResponseEntity.ok(getVideosUseCase.getVideos(id));
    }
}
