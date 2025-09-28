package com.rustplusplus.credentials.exception;

public class RequestNotFoundException extends RuntimeException {

    public RequestNotFoundException(long id) {
        super("Credential request " + id + " was not found");
    }
}
