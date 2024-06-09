package com.example.multimodule.core.api.controller;

import com.example.multimodule.core.api.controller.request.PostInputRequest;
import com.example.multimodule.core.api.controller.response.CreatedResponse;
import com.example.multimodule.core.domain.domain.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
