package com.example.multimodule.core.api.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Date;

@Getter
public class ErrorResponse {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final Date timestamp = new Date();
    private final Integer status;
    private final String message;
    private final String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String details;

    public ErrorResponse(Integer status, String path, String message) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.details = null;
    }

    public ErrorResponse(Integer status, String path, String message, String details) {
        this.status = status;
        this.path = path;
        this.message = message;
        this.details = details;
    }
}
