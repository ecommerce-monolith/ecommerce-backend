package com.huyhn.ecommerce_backend.module.product.request;

import java.math.BigDecimal;

public record ProductPageRequest(
        String name,
        BigDecimal minPrice,
        BigDecimal maxPrice) {
}
