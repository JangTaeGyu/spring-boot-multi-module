package com.example.multimodule.core.domain.domain.post;

public interface PostTagRepository {
    void create(Long postId, Long tagId);
}
