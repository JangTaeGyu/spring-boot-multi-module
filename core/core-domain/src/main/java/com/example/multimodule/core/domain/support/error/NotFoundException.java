package com.example.multimodule.core.domain.support.error;

import lombok.Getter;

@Getter
public class NotFoundException extends HttpException {
    private final String target;
    private final String key;
    private final Object value;

    public NotFoundException(String target, String key, Object value) {
        super(String.format("not found %s by %s %s", target, key, value));

        this.target = target;
        this.key = key;
        this.value = value;
    }
}
