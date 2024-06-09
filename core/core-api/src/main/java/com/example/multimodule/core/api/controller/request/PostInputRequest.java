package com.example.multimodule.core.api.controller.request;

import com.example.multimodule.core.domain.domain.post.PostData;
import com.example.multimodule.core.domain.domain.post.PostTagData;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class PostInputRequest {
    @NotNull
    private Long categoryId;

    @NotBlank
    private String title;

    @NotBlank
    private String body;

    private List<String> tagNames;

    public PostData toData() {
        return new PostData(categoryId, title, body);
    }

    public PostTagData toTagData() {
        return new PostTagData(tagNames);
    }
}
