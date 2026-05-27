package com.spotter_proyect.spotter.core.shared.DTO;

import jakarta.validation.constraints.NotBlank;

public record ContactRequestRequest(
    @NotBlank(message = "Message is required")
    String message
) {}
