package com.huyhn.ecommerce_backend.module.product.dto;

import java.util.UUID;

public record AttributeValueDTO(
        UUID id,
        String name,
        String attributeName
) {
    public AttributeValueDTO(UUID id, String name) {
        this(id, name, null);
    }
}
