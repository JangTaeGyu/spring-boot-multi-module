package com.example.multimodule.core.api.controller.response;

import lombok.Getter;

@Getter
public class PaginationResponse<T> {
    private final T data;
    private final PaginatedData pagination;

    public PaginationResponse(T data, PaginatedData pagination) {
        this.data = data;
        this.pagination = pagination;
    }
}
