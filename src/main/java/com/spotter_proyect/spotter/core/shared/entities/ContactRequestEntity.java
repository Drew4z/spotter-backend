package com.spotter_proyect.spotter.core.shared.entities;

import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "contact_requests")
@Data
public class ContactRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerEntity trainer;

    @Enumerated(EnumType.STRING)
    private RequestStatus status;

    private String message;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.status = RequestStatus.PENDING; // Por defecto
    }
}