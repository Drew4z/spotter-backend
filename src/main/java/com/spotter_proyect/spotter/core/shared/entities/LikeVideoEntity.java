package com.spotter_proyect.spotter.core.shared.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "video_likes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "video_id"}) // <--- EVITA DUPLICADOS
        }
)
@Data @NoArgsConstructor
public class LikeVideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user; // Puede dar like un cliente o un trainer

    @ManyToOne
    @JoinColumn(name = "video_id", nullable = false)
    private VideoEntity video;

    private LocalDateTime likedAt;

    public LikeVideoEntity(UserEntity user, VideoEntity video) {
        this.user = user;
        this.video = video;
        likedAt = LocalDateTime.now();
    }
}
