package com.rustplusplus.credentials.model;

import java.time.Instant;
import java.util.Objects;

public class CredentialRequest {

    private final long id;
    private final String email;
    private final String discordTag;
    private final String steamId;
    private final String justification;
    private final Instant createdAt;
    private RequestStatus status;

    public CredentialRequest(
            long id,
            String email,
            String discordTag,
            String steamId,
            String justification,
            RequestStatus status,
            Instant createdAt) {
        this.id = id;
        this.email = Objects.requireNonNull(email, "email");
        this.discordTag = Objects.requireNonNull(discordTag, "discordTag");
        this.steamId = Objects.requireNonNull(steamId, "steamId");
        this.justification = Objects.requireNonNull(justification, "justification");
        this.status = Objects.requireNonNull(status, "status");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getDiscordTag() {
        return discordTag;
    }

    public String getSteamId() {
        return steamId;
    }

    public String getJustification() {
        return justification;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = Objects.requireNonNull(status, "status");
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
