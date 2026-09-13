package com.huyhn.ecommerce_backend.module.product.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateProductRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductDetailResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;

public interface ProductService {
    ProductPageResponse getPage(ProductPageRequest request, Pageable pageable);

    ProductCreatedResponse createProduct(CreateProductRequest request);

    ProductDetailResponse getById(UUID id);

    ProductDetailResponse updateProduct(UUID id, UpdateProductRequest request);
}
