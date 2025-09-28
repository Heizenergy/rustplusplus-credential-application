package com.rustplusplus.credentials.dto;

import com.rustplusplus.credentials.model.CredentialRequest;
import com.rustplusplus.credentials.model.RequestStatus;
import java.time.Instant;

public record CredentialRequestResponse(
        long id,
        String email,
        String discordTag,
        String steamId,
        String justification,
        RequestStatus status,
        Instant createdAt
) {

    public static CredentialRequestResponse fromModel(CredentialRequest request) {
        return new CredentialRequestResponse(
                request.getId(),
                request.getEmail(),
                request.getDiscordTag(),
                request.getSteamId(),
                request.getJustification(),
                request.getStatus(),
                request.getCreatedAt()
        );
    }
}
