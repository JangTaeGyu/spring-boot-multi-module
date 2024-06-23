package com.example.multimodule.core.domain.domain.post;

import java.util.Optional;

public interface PostCache {
    void save(Post post);

    Optional<Post> getById(Long id);

    void deleteById(Long id);
}
