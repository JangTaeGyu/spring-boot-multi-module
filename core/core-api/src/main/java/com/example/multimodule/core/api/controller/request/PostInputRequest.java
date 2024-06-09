package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.post.PostData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class PostInputRequest {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    public PostData toData() {
        return new PostData(categoryId, title, body);
    }
}
