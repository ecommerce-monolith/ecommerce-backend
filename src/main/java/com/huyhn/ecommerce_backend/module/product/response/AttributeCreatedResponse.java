package com.huyhn.ecommerce_backend.module.product.response;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeValueItem;

import java.util.List;
import java.util.UUID;

public record AttributeCreatedResponse(
        UUID id,
        String name,
        List<AttributeValueItem> values
) {
}
