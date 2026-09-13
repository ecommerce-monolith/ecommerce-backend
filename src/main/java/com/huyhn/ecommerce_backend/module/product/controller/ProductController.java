package com.huyhn.ecommerce_backend.module.product.controller;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateProductRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductDetailResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Product", description = "Product API")
@RequestMapping("/api/v1/admin/products")
public interface ProductController {

    @Operation(summary = "Get a page of products")
    @GetMapping
    ResponseEntity<ProductPageResponse> getPage(@ParameterObject ProductPageRequest request,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Create a new product")
    @PostMapping
    ResponseEntity<ProductCreatedResponse> create(@RequestBody @Validated CreateProductRequest request);

    @Operation(summary = "Get a product by id")
    @GetMapping("/{id}")
    ResponseEntity<ProductDetailResponse> getById(@PathVariable UUID id);

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    ResponseEntity<ProductDetailResponse> update(@PathVariable UUID id,
            @RequestBody @Validated UpdateProductRequest request);
}
