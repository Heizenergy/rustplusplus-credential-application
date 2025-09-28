package com.rustplusplus.credentials.controller;

import com.rustplusplus.credentials.dto.CreateCredentialRequest;
import com.rustplusplus.credentials.dto.CredentialRequestResponse;
import com.rustplusplus.credentials.dto.UpdateStatusRequest;
import com.rustplusplus.credentials.model.RequestStatus;
import com.rustplusplus.credentials.service.CredentialRequestService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/credentials")
public class CredentialRequestController {

    private final CredentialRequestService service;

    public CredentialRequestController(CredentialRequestService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CredentialRequestResponse create(@Valid @RequestBody CreateCredentialRequest payload) {
        return service.create(payload);
    }

    @GetMapping
    public List<CredentialRequestResponse> findAll(@RequestParam(value = "status", required = false) RequestStatus status) {
        return service.findAll(status);
    }

    @PutMapping("/{id}/status")
    public CredentialRequestResponse updateStatus(
            @PathVariable long id,
            @Valid @RequestBody UpdateStatusRequest payload) {
        return service.updateStatus(id, payload);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        service.delete(id);
    }
}
