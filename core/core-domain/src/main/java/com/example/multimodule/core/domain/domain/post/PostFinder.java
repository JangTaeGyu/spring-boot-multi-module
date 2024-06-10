package com.example.multimodule.core.domain.domain.post;

import com.example.multimodule.core.domain.support.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostFinder {
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;

    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .map(post -> {
                    List<PostTag> tags = postTagRepository.findAllByPostIds(List.of(postId));
                    post.setTags(tags);
                    return post;
                })
                .orElseThrow(() -> new NotFoundException("Post", "id", postId));
    }
}
