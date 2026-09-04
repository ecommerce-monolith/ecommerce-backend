package com.huyhn.ecommerce_backend.module.product.request;

import com.huyhn.ecommerce_backend.module.product.dto.SkuItem;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProductRequest(
        @NotNull(message = "Product name is required")
        String name,

        List<SkuItem> skus
) {
}
