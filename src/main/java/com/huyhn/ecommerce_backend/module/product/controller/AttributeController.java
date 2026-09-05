package com.huyhn.ecommerce_backend.module.product.controller;

import com.huyhn.ecommerce_backend.module.product.request.AttributeFilterRequest;
import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.AttributePageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Attribute", description = "Attribute API")
@RequestMapping("/api/v1/admin/attributes")
public interface AttributeController {
    @Operation(summary = "Get attribute page", description = "Retrieve a page of attributes")
    @GetMapping
    ResponseEntity<AttributePageResponse> getPage(@ParameterObject AttributeFilterRequest request, @ParameterObject Pageable pageable);

    @Operation(summary = "Create attribute", description = "Create a new attribute with its values")
    @PostMapping
    ResponseEntity<AttributeCreatedResponse> create(@RequestBody CreateAttributeRequest request);
}
