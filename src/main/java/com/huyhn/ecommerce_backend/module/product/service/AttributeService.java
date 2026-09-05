package com.huyhn.ecommerce_backend.module.product.service;

import com.huyhn.ecommerce_backend.module.product.request.AttributeFilterRequest;
import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.AttributePageResponse;
import org.springframework.data.domain.Pageable;

public interface AttributeService {
    AttributeCreatedResponse create(CreateAttributeRequest request);

    AttributePageResponse getPage(AttributeFilterRequest request, Pageable pageable);
}
