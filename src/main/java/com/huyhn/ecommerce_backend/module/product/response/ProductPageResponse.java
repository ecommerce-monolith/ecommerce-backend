package com.huyhn.ecommerce_backend.module.product.response;

import com.huyhn.ecommerce_backend.module.product.dto.ProductItemDTO;

import java.util.List;

public record ProductPageResponse(
        List<ProductItemDTO> products,
        long totalElements,
        long totalPages,
        int currentPage,
        int pageSize
) {
}
