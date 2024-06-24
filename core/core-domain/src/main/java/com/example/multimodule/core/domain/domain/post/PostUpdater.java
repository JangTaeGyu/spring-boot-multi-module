package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostUpdater {
    private final PostValidation postValidation;
    private final PostRepository postRepository;
    private final PostCache postCache;

    public void updatePost(Long postId, PostData data) {
        postValidation.validate(data);
        postRepository.updateById(postId, data.toDomain());
        postCache.deleteById(postId);
    }

    public void changePostShow(Long postId, boolean show) {
        postRepository.setShow(postId, show);
        postCache.deleteById(postId);
    }
}
