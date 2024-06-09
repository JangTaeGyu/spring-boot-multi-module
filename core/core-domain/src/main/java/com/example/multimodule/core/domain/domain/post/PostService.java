package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;
    private final PostTagManager postTagManager;

    public Long createPost(PostData data, PostTagData tagData) {
        Long createdPostId = postCreator.createPost(data);
        postTagManager.attachTagsToPost(createdPostId, tagData);
        return createdPostId;
    }
}
