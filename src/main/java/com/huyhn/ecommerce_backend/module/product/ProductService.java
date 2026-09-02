package com.huyhn.ecommerce_backend.module.product;

import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductPageResponse getPage(ProductPageRequest request, Pageable pageable);
}
