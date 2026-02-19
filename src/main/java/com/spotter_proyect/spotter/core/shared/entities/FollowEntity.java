package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.model.Client;
import com.spotter_proyect.spotter.core.shared.model.Trainer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "trainer_followers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"client_id", "trainer_id"}) // ¡Evita seguidores duplicados!
})
public class FollowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Quién sigue (El Cliente)
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Long clientId;

    // A quién siguen (El Entrenador)
    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Long trainerId;

    private LocalDateTime createdAt = LocalDateTime.now();

}
