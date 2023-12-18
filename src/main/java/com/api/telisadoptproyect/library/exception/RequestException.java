package com.api.telisadoptproyect.library.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class RequestException extends ResponseStatusException {
    private final String message;

    public RequestException(HttpStatusCode status, String message) {
        super(status, message);
        this.message = message;
    }

    @Override
    public String getMessage(){return this.message;}
}
