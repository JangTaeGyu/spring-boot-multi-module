package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.domain.tag.Tag;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTag extends Tag {
    @JsonIgnore
    private Long postId;

    public PostTag(Long id, String name, Long postId) {
        super(id, name);
        this.postId = postId;
    }
}
