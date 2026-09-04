package com.huyhn.ecommerce_backend.module.product.service;

import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;

public interface AttributeService {
    AttributeCreatedResponse create(CreateAttributeRequest request);
}
