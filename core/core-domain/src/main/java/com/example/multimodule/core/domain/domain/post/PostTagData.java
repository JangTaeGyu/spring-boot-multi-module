package com.example.multimodule.core.domain.domain.post;

import lombok.Getter;

import java.util.List;

@Getter
public class PostTagData {
    private final List<String> tagNames;

    public PostTagData(List<String> tagNames) {
        this.tagNames = tagNames;
    }
}
