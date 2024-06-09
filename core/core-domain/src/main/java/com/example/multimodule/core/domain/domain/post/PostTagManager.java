package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTagManager {
    private final PostTagRepository postTagRepository;

    public void attachTagsToPost(Long postId, PostTagData tagData) {
    }
}
