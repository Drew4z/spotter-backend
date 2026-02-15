package com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.infrastructure.adapter.in;


import com.spotter_proyect.spotter.core.useCases.trainer.videos.deleteVideos.application.port.in.DeleteVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainer/video")
public class DeleteVideoController {

    private final DeleteVideoUseCase deleteVideoUseCase;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id){

        return ResponseEntity.ok(deleteVideoUseCase.deleteVideo(id));
    }
}
