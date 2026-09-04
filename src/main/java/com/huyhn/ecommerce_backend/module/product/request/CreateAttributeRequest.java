package com.huyhn.ecommerce_backend.module.product.request;

import java.util.List;

public record CreateAttributeRequest(
        String name,
        List<String> values
) {
}
