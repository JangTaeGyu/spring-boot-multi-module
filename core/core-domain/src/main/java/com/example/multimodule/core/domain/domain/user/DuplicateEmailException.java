package com.example.multimodule.core.domain.domain.user;

import com.example.multimodule.core.domain.support.error.HttpException;

public class DuplicateEmailException extends HttpException {
    public DuplicateEmailException(String message) {
        super(message);
    }
}
