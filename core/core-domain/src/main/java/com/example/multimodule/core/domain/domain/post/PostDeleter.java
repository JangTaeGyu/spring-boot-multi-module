package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostDeleter {
    private final PostRepository postRepository;

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
