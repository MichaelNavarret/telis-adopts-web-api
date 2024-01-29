package com.api.telisadoptproyect.api.controller;

import com.api.telisadoptproyect.api.response.ErrorResponse;
import com.api.telisadoptproyect.library.exception.RequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException (Exception e) throws Exception {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse("internal_server_error");

        if (e instanceof RequestException) {
            RequestException rex = (RequestException) e;
            if (rex != null) {
                httpStatus = rex.getStatus();
                errorResponse.setMessage(rex.getMessage());
            }
        } else if (e instanceof HttpMessageConversionException) {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorResponse.setMessage("malformed_request");
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            httpStatus = HttpStatus.METHOD_NOT_ALLOWED;
            errorResponse.setMessage("method_not_allowed");
        } else if (e instanceof MissingRequestHeaderException){
            httpStatus = HttpStatus.BAD_REQUEST;
            errorResponse.setMessage("missing_request_header");
        } else if (e instanceof AccessDeniedException) {
            httpStatus = HttpStatus.FORBIDDEN;
            errorResponse.setMessage("You don't have permission to access this resource");

        } else{
            throw e;
        }

        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
    }
}
