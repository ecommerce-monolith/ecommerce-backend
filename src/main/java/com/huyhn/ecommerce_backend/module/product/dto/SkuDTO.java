package com.huyhn.ecommerce_backend.module.product.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record SkuDTO(
        UUID id,

        @NotNull(message = "SKU name is required")
        String name,

        BigDecimal price,

        @Min(value = 0, message = "Quantity must be greater than or equal to 0")
        Integer quantity,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description,
        
        UUID warehouseId,

        List<AttributeValueDTO> attributeValues
) {
}
