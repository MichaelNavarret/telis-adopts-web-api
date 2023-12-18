package com.api.telisadoptproyect.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseResponse {
    public enum Status {
        SUCCESS,
        ERROR
    }

    private Status status;
    private Integer code;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public BaseResponse(Status status, Integer code) {
        this.status = status;
        this.code = code;
    }

    public BaseResponse(Status status, Integer code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
