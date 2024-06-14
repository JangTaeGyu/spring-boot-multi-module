package com.example.multimodule.core.domain.domain.post;

import java.util.Optional;

public interface PostRepository {
    Long create(Post post);

    Optional<Post> findById(Long postId);

    void updateById(Long id, Post post);

    void setShow(Long id, boolean show);
}
