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

    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "category")
    private VideoCategory category;

    @Column(name="videoUrl")
    private String videoUrl; // URL de Cloudinary

    // Relación: Muchos videos pertenecen a UN entrenador
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private UserEntity trainer;

    @Column(name="likesCount")
    private Integer likesCount = 0; // Contador caché

    @Column(name="createdAt")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
