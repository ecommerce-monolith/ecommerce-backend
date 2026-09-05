package com.huyhn.ecommerce_backend.module.product.service;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeDTO;
import com.huyhn.ecommerce_backend.module.product.request.AttributePageRequest;
import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AttributeService {
    AttributeCreatedResponse create(CreateAttributeRequest request);

    Page<AttributeDTO> getPage(AttributePageRequest request, Pageable pageable);
}
