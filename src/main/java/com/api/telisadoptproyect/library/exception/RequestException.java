package com.api.telisadoptproyect.library.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.lang.NonNull;
import org.springframework.web.server.ResponseStatusException;

public class RequestException extends ResponseStatusException {


    private final String message;
    private final HttpStatus status;

    public RequestException(HttpStatus status, String message) {
        super(status, message);
        this.message = message;
        this.status = status;
    }

    @Override
    @NonNull
    public String getMessage(){return this.message;}

    public HttpStatus getStatus() {
        return status;
    }
}
