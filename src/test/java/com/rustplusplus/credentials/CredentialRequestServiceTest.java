package com.rustplusplus.credentials;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.rustplusplus.credentials.dto.CreateCredentialRequest;
import com.rustplusplus.credentials.dto.UpdateStatusRequest;
import com.rustplusplus.credentials.exception.RequestNotFoundException;
import com.rustplusplus.credentials.model.RequestStatus;
import com.rustplusplus.credentials.service.CredentialRequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CredentialRequestServiceTest {

    private CredentialRequestService service;

    @BeforeEach
    void setUp() {
        service = new CredentialRequestService();
    }

    @Test
    void createStoresNewRequestWithPendingStatus() {
        var payload = new CreateCredentialRequest("player@example.com", "Player#1234", "76561198000000000", "Need access");

        var response = service.create(payload);

        assertThat(response.id()).isGreaterThan(0);
        assertThat(response.status()).isEqualTo(RequestStatus.PENDING);
        assertThat(response.email()).isEqualTo(payload.email());
    }

    @Test
    void updateStatusChangesRequestStatus() {
        var response = service.create(new CreateCredentialRequest("player@example.com", "Player#1234", "76561198000000000", "Need access"));
        var updated = service.updateStatus(response.id(), new UpdateStatusRequest(RequestStatus.APPROVED));

        assertThat(updated.status()).isEqualTo(RequestStatus.APPROVED);
    }

    @Test
    void updateStatusThrowsWhenRequestMissing() {
        assertThrows(RequestNotFoundException.class, () -> service.updateStatus(42L, new UpdateStatusRequest(RequestStatus.REJECTED)));
    }
}
