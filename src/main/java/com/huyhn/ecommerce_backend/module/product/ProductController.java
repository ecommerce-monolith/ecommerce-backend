package com.huyhn.ecommerce_backend.module.product;

import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ProductPageResponse> getProducts(@ParameterObject ProductPageRequest request,
                                                           @ParameterObject Pageable pageable) {
        ProductPageResponse response = productService.getPage(request, pageable);
        return ResponseEntity.ok(response);
    }
}
