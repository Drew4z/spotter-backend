package com.spotter_proyect.spotter.core.shared.DTO;

import com.spotter_proyect.spotter.core.shared.enums.RequestStatus;
import jakarta.validation.constraints.NotNull;

public record RespondRequestRequest(
    @NotNull(message = "Status is required")
    RequestStatus status  // ACCEPTED or REJECTED
) {}
