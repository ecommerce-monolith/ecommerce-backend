package com.huyhn.ecommerce_backend.module.product.response;

import com.huyhn.ecommerce_backend.module.product.dto.ProductItem;
import com.huyhn.ecommerce_backend.shared.response.PageResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ProductPageResponse extends PageResponse<ProductItem> {
}
