package com.huyhn.ecommerce_backend.module.product.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huyhn.ecommerce_backend.module.product.dto.AttributeValueDTO;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AttributeCreatedResponse(
        UUID id,
        String name,
        List<AttributeValueDTO> values
) {
    public AttributeCreatedResponse(UUID id, String name) {
        this(id, name, null);
    }
}
