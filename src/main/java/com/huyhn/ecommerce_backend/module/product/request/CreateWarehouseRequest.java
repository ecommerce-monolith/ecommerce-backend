package com.huyhn.ecommerce_backend.module.product.request;

import jakarta.validation.constraints.NotBlank;

public record CreateWarehouseRequest(
        @NotBlank(message = "Warehouse name is required")
        String name,
        String address,
        String city,
        String state
) {
}
