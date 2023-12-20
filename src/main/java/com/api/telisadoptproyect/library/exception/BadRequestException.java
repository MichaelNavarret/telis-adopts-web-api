package com.api.telisadoptproyect.library.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends RequestException{

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
