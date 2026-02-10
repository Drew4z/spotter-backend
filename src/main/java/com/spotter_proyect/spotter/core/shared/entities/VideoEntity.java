package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.enums.VideoCategory;
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

    @Column(name="title")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private VideoCategory category;

    @Column(name="videoUrl")
    private String videoUrl; // URL de Cloudinary

    // Relación N:1: N vídeos pertenecen a 1 entrenador
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerEntity trainer;

    @Column(name="likesCount")
    private int likesCount = 0; // Contador caché

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
