package com.huyhn.ecommerce_backend.module.product.controller.impl;

import com.huyhn.ecommerce_backend.module.product.controller.ProductController;
import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import com.huyhn.ecommerce_backend.module.product.service.ProductService;
import com.huyhn.ecommerce_backend.shared.constants.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@Secured(AuthoritiesConstants.ADMIN)
@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {
    private final ProductService productService;

    @Override
    public ResponseEntity<ProductPageResponse> getPage(ProductPageRequest request,
                                                       Pageable pageable) {
        ProductPageResponse response = productService.getPage(request, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ProductCreatedResponse> create(CreateProductRequest request) {
        ProductCreatedResponse response = productService.createProduct(request);
        return ResponseEntity.ok(response);
    }
}
