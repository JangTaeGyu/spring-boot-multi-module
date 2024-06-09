package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostCreator {
    private final PostRepository postRepository;

    public Long createPost(PostData data) {
        return postRepository.create(data.toDomain());
    }
}
