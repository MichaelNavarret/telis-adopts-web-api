package com.api.telisadoptproyect.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorResponse {
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    public ErrorResponse(String message){this.message = message;}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
