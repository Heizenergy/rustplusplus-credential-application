package com.rustplusplus.credentials.dto;

import com.rustplusplus.credentials.model.RequestStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
        @NotNull(message = "Status is required")
        RequestStatus status
) {
}
