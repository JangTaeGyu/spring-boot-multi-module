package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.support.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostRepository postRepository;
    private final PostCache postCache;
    private final PostTagRepository postTagRepository;

    private void mergeTagsToPost(List<Post> posts) {
        if (posts.isEmpty()) return;

        List<Long> postIds = posts.stream().map(Post::getId).toList();
        List<PostTag> tags = postTagRepository.findAllByPostIds(postIds);

        posts.forEach(post -> {
            List<PostTag> filteredTags = tags.stream().filter(tag -> tag.getPostId().equals(post.getId())).toList();
            post.setTags(filteredTags);
        });
    }

    public Page<Post> searchPostsBy(PostSearchData searchData, Pageable pageable) {
        Page<Post> posts = postRepository.searchBy(searchData, pageable);
        mergeTagsToPost(posts.getContent());
        return posts;
    }

    public Post getPost(Long postId) {
        return postCache.getById(postId)
                .orElseGet(() -> postRepository.findById(postId)
                        .map(post -> {
                            mergeTagsToPost(List.of(post));
                            postCache.save(post);
                            return post;
                        })
                        .orElseThrow(() -> new NotFoundException("Post", "id", postId))
                );
    }
}
