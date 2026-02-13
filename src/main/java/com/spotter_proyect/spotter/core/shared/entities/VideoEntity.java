package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.useCases.trainer.videos.sharedVideos.enums.VideoCategory;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "videos")
@Data
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VideoCategory category;

    private String videoUrl; // URL de Cloudinary

    // Relación: Muchos videos pertenecen a UN entrenador
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerEntity trainer;
    private Integer likesCount = 0; // Contador caché
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
