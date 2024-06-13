package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.support.transaction.TransactionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostFinder postFinder;
    private final PostCreator postCreator;
    private final PostUpdater postUpdater;

    private final PostTagManager postTagManager;

    private final TransactionHandler transactionHandler;

    public Long createPost(PostData data, PostTagData tagData) {
        return transactionHandler.execute(() -> {
            Long createdPostId = postCreator.createPost(data);
            postTagManager.attachTagsToPost(createdPostId, tagData);
            return createdPostId;
        });
    }

    public Post getPost(Long postId) {
        return postFinder.getPost(postId);
    }

    public void updatePost(Long postId, PostData data) {
        postUpdater.updatePost(postId, data);
    }
}
