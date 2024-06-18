package com.example.multimodule.core.domain.domain.user;

import com.example.multimodule.core.domain.support.error.HttpException;
import lombok.Getter;

@Getter
public class JwtTokenException extends HttpException {
    public JwtTokenException(String message) {
        super(message);
    }
}
