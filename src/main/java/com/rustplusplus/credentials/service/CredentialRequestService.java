package com.rustplusplus.credentials.service;

import com.rustplusplus.credentials.dto.CreateCredentialRequest;
import com.rustplusplus.credentials.dto.CredentialRequestResponse;
import com.rustplusplus.credentials.dto.UpdateStatusRequest;
import com.rustplusplus.credentials.exception.RequestNotFoundException;
import com.rustplusplus.credentials.model.CredentialRequest;
import com.rustplusplus.credentials.model.RequestStatus;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CredentialRequestService {

    private final AtomicLong sequence = new AtomicLong();
    private final Map<Long, CredentialRequest> storage = new ConcurrentHashMap<>();

    public CredentialRequestResponse create(CreateCredentialRequest payload) {
        long id = sequence.incrementAndGet();
        CredentialRequest request = new CredentialRequest(
                id,
                payload.email(),
                payload.discordTag(),
                payload.steamId(),
                payload.justification(),
                RequestStatus.PENDING,
                Instant.now());
        storage.put(id, request);
        return CredentialRequestResponse.fromModel(request);
    }

    public List<CredentialRequestResponse> findAll(RequestStatus status) {
        return storage.values().stream()
                .filter(request -> status == null || request.getStatus() == status)
                .sorted(Comparator.comparing(CredentialRequest::getCreatedAt).reversed())
                .map(CredentialRequestResponse::fromModel)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public CredentialRequestResponse updateStatus(long id, UpdateStatusRequest payload) {
        CredentialRequest request = storage.get(id);
        if (request == null) {
            throw new RequestNotFoundException(id);
        }
        request.setStatus(payload.status());
        return CredentialRequestResponse.fromModel(request);
    }

    public void delete(long id) {
        if (storage.remove(id) == null) {
            throw new RequestNotFoundException(id);
        }
    }
}
