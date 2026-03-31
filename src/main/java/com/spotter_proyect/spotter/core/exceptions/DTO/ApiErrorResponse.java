
package com.spotter_proyect.spotter.core.exceptions.DTO;

import lombok.Data;
import java.time.LocalDateTime;


public record ApiErrorResponse(
        String message,
        int status,
        LocalDateTime timestamp
    )
{}



