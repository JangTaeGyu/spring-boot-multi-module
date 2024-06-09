package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.support.transaction.TransactionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostCreator postCreator;

    private final PostTagManager postTagManager;

    private final TransactionHandler transactionHandler;

    public Long createPost(PostData data, PostTagData tagData) {
        return transactionHandler.execute(() -> {
            Long createdPostId = postCreator.createPost(data);
            postTagManager.attachTagsToPost(createdPostId, tagData);
            return createdPostId;
        });
    }
}
