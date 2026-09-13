package com.huyhn.ecommerce_backend.module.product.request;

import com.huyhn.ecommerce_backend.module.product.dto.SkuDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateProductRequest(
        @NotNull(message = "Product name is required")
        String name,

        List<SkuDTO> skus
) {
}
