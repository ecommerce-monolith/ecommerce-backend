package com.huyhn.ecommerce_backend.module.product.service;

import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductPageResponse getPage(ProductPageRequest request, Pageable pageable);

    ProductCreatedResponse createProduct(CreateProductRequest request);
}
