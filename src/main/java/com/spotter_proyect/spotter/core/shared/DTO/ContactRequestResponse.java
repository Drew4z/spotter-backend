package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;

import java.time.LocalDateTime;

public record ContactRequestResponse(
    Long id,
    String trainerName,
    String clientName,
    String message,
    RequestStatus status,
    LocalDateTime createdAt
) {}
