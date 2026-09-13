package com.huyhn.ecommerce_backend.module.product.response;

import com.huyhn.ecommerce_backend.module.product.dto.SkuDTO;

import java.util.List;
import java.util.UUID;

public record ProductDetailResponse(
        UUID id,
        String name,
        String description,
        List<SkuDTO> skus
) {
}
