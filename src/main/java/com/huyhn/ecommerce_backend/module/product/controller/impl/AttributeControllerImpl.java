package com.huyhn.ecommerce_backend.module.product.controller.impl;

import com.huyhn.ecommerce_backend.module.product.controller.AttributeController;
import com.huyhn.ecommerce_backend.module.product.request.AttributePageRequest;
import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.AttributePageResponse;
import com.huyhn.ecommerce_backend.module.product.service.AttributeService;
import com.huyhn.ecommerce_backend.shared.constants.AuthoritiesConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RestController;

@Secured(AuthoritiesConstants.ADMIN)
@RestController
@RequiredArgsConstructor
public class AttributeControllerImpl implements AttributeController {
    private final AttributeService attributeService;

    @Override
    public ResponseEntity<AttributePageResponse> getPage(AttributePageRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<AttributeCreatedResponse> create(CreateAttributeRequest request) {
        AttributeCreatedResponse response = attributeService.create(request);
        return ResponseEntity.ok(response);
    }
}
