package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class PostTag extends Tag {
    @JsonIgnore
    private final Long postId;

    public PostTag(Long id, String name, Long postId) {
        super(id, name);
        this.postId = postId;
    }
}
