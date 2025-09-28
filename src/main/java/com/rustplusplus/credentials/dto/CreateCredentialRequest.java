package com.rustplusplus.credentials.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateCredentialRequest(
        @Email(message = "Must be a valid email")
        String email,

        @NotBlank(message = "Discord tag is required")
        String discordTag,

        @NotBlank(message = "Steam ID is required")
        String steamId,

        @NotBlank(message = "Justification is required")
        String justification
) {
}
