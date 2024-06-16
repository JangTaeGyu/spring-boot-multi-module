package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.support.transaction.TransactionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostFinder postFinder;
    private final PostCreator postCreator;
    private final PostUpdater postUpdater;
    private final PostDeleter postDeleter;

    private final PostTagManager postTagManager;

    private final TransactionHandler transactionHandler;

    public Page<Post> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        return postFinder.searchPostsBy(searchData, pageable);
    }

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

    public void updatePost(Long postId, PostData data, PostTagData tagData) {
        transactionHandler.execute(() -> {
            postUpdater.updatePost(postId, data);
            postTagManager.syncTagsToPost(postId, tagData);
            return null;
        });
    }

    public void changePostShow(Long postId, boolean show) {
        postUpdater.changePostShow(postId, show);
    }

    public void deletePost(Long postId) {
        transactionHandler.execute(() -> {
            postTagManager.detachTagsToPost(postId);
            postDeleter.deletePost(postId);
            return null;
        });
    }
}
