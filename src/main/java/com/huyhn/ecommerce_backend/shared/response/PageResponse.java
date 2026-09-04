package com.huyhn.ecommerce_backend.shared.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private long totalElements;
    private long totalPages;
    private int currentPage;
    private int pageSize;
}
