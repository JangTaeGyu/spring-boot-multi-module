package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.domain.tag.TagCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PostTagManager {
    private final TagCreator tagCreator;
    private final PostTagRepository postTagRepository;

    public void attachTagsToPost(Long postId, PostTagData tagData) {
        tagData.getTagNames().forEach(tagName -> {
            Long createdTagId = tagCreator.getOrCreateTagId(tagName);
            postTagRepository.create(postId, createdTagId);
        });
    }

    public void detachTagsToPost(Long postId) {
        postTagRepository.deleteByPostId(postId);
    }

    public void syncTagsToPost(Long postId, PostTagData tagData) {
        detachTagsToPost(postId);
        attachTagsToPost(postId, tagData);
    }
}
