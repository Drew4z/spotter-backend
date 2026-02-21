package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.enums.SpecialityTrainer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Entity
@Table(name = "trainers")
@PrimaryKeyJoinColumn(name = "user_id") // Usa el mismo ID que User
@Data
@EqualsAndHashCode(callSuper = true) // Necesario al heredar con Lombok
public class TrainerEntity extends UserEntity {

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Enumerated(EnumType.STRING)
    @Column(name="specialty")
    private SpecialityTrainer specialty; // Yoga, Crossfit...

    private String phoneNumber; // El dato "oculto" hasta el match

    @Column(name="isVerified")
    private Boolean isVerified = false;

    // Relación: Un entrenador tiene muchos videos
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VideoEntity> videos;
}
