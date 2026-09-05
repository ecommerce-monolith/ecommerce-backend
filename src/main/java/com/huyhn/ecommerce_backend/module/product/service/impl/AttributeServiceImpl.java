package com.huyhn.ecommerce_backend.module.product.service.impl;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Attribute;
import com.huyhn.ecommerce_backend.module.product.entity.AttributeValue;
import com.huyhn.ecommerce_backend.module.product.mapper.AttributeMapper;
import com.huyhn.ecommerce_backend.module.product.repository.AttributeRepository;
import com.huyhn.ecommerce_backend.module.product.repository.AttributeValueRepository;
import com.huyhn.ecommerce_backend.module.product.request.AttributePageRequest;
import com.huyhn.ecommerce_backend.module.product.request.CreateAttributeRequest;
import com.huyhn.ecommerce_backend.module.product.response.AttributeCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.service.AttributeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AttributeServiceImpl implements AttributeService {
    private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;

    private final AttributeMapper attributeMapper;

    @Override
    public AttributeCreatedResponse create(CreateAttributeRequest request) {
        Attribute attribute = new Attribute();
        attribute.setName(request.name());
        attributeRepository.save(attribute);

        List<AttributeValue> attributeValues = request.values().stream().map(value -> {
            AttributeValue attributeValue = new AttributeValue();
            attributeValue.setValue(value);
            attributeValue.setAttributeId(attribute.getId());
            return attributeValue;
        }).toList();

        attributeValueRepository.saveAll(attributeValues);

        return new AttributeCreatedResponse(attribute.getId(), attribute.getName());
    }

    @Override
    public Page<AttributeDTO> getPage(AttributePageRequest request, Pageable pageable) {
        return attributeRepository.findAll(pageable).map(attributeMapper::toDto);
    }
}
