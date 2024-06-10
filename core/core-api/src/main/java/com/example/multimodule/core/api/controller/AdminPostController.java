package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.request.PostInputRequest;
import com.example.multimodule.core.api.controller.response.CreatedResponse;
import com.example.multimodule.core.api.controller.response.SuccessfulResponse;
import com.example.multimodule.core.domain.domain.post.Post;
import com.example.multimodule.core.domain.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/posts")
@RequiredArgsConstructor
public class AdminPostController {
    private final PostService postService;

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
}
