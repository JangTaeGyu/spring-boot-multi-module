package com.example.multimodule.core.api.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@Getter
@RequiredArgsConstructor
public class PaginatedData {
    private final int currentPage;
    private final int perPage;
    private final long total;
    private final int totalPage;
    private final boolean isLast;

    public static PaginatedData of(Page<?> data) {
        return new PaginatedData(
                data.getNumber(),
                data.getSize(),
                data.getTotalElements(),
                data.getTotalPages(),
                data.isLast()
        );
    }
}
