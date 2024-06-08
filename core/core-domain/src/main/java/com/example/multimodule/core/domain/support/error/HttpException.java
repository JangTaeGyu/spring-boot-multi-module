package com.example.multimodule.core.domain.support.error;

import lombok.Getter;

@Getter
public class HttpException extends RuntimeException {
    public HttpException(String message) {
        super(message);
    }
}
