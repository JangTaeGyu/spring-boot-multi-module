package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostValidation postValidation;
    private final PostRepository postRepository;

    public void updatePost(Long postId, PostData data) {
        postValidation.validate(data);
        postRepository.updateById(postId, data.toDomain());
    }

    public void changePostShow(Long postId, boolean show) {
        postRepository.setShow(postId, show);
    }
}
