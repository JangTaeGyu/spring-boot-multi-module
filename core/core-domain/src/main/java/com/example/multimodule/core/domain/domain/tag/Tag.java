package com.example.multimodule.core.domain.domain.tag;

import lombok.Getter;

@Getter
public class Tag {
    private final Long id;
    private final String name;

    public Tag(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
