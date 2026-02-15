package com.spotter_proyect.spotter.core.useCases.client.likeVideo.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.useCases.client.likeVideo.application.port.in.LikeVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client/video")
public class LikeVideoController {

    private final LikeVideoUseCase likeVideoUseCase;

    @PostMapping("/like/{id}")
    public ResponseEntity<String> likeVideo(@PathVariable Long id){

        return ResponseEntity.ok(likeVideoUseCase.likeVideo(id));
    }
}
