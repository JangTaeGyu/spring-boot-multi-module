package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.constant.PageConstant;
import com.example.multimodule.core.api.controller.request.PostInputRequest;
import com.example.multimodule.core.api.controller.response.CreatedResponse;
import com.example.multimodule.core.api.controller.response.PaginatedData;
import com.example.multimodule.core.api.controller.response.PaginationResponse;
import com.example.multimodule.core.api.controller.response.SuccessfulResponse;
import com.example.multimodule.core.domain.domain.post.Post;
import com.example.multimodule.core.domain.domain.post.PostSearchData;
import com.example.multimodule.core.domain.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/posts")
@RequiredArgsConstructor
public class AdminPostController {
    private final PostService postService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) Long categoryId,
            @RequestParam(defaultValue = "", required = false) Boolean show,
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        PostSearchData request = new PostSearchData(categoryId, show, keyword);
        Page<Post> pagePost = postService.searchPostsBy(request, PageRequest.of(page, perPage));
        PaginationResponse<List<Post>> response = new PaginationResponse<>(pagePost.getContent(), PaginatedData.of(pagePost));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid PostInputRequest request) {
        Long postId = postService.createPost(request.toData(), request.toTagData());
        CreatedResponse response = new CreatedResponse(postId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> show(@PathVariable Long postId) {
        Post post = postService.getPost(postId);
        SuccessfulResponse<Post> response = new SuccessfulResponse<>(post);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Void> update(@PathVariable Long postId, @RequestBody @Valid PostInputRequest request) {
        postService.updatePost(postId, request.toData(), request.toTagData());
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{postId}/enable")
    public ResponseEntity<Void> enable(@PathVariable Long postId) {
        postService.changePostShow(postId, true);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{postId}/disable")
    public ResponseEntity<Void> disable(@PathVariable Long postId) {
        postService.changePostShow(postId, false);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
