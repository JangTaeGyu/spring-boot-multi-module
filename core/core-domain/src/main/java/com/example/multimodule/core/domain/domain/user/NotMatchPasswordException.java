package com.example.multimodule.core.domain.domain.user;

import com.example.multimodule.core.domain.support.error.HttpException;

public class NotMatchPasswordException extends HttpException {
    public NotMatchPasswordException(String message) {
        super(message);
    }
}
