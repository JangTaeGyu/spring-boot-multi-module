package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.response.ErrorResponse;
import com.example.multimodule.core.domain.support.error.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(AuthenticationException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(httpStatus.value(), request.getRequestURI(), e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<ErrorResponse> handleHttpException(HttpException e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(httpStatus.value(), request.getRequestURI(), e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = new ErrorResponse(httpStatus.value(), request.getRequestURI(), "Server Error", e.getMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
