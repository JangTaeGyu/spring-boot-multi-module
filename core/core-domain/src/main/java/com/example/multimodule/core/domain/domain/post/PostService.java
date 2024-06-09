package com.example.multimodule.core.domain.domain.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;

    public Long createPost(PostData data, PostTagData tagData) {
        return postCreator.createPost(data);
    }
}
