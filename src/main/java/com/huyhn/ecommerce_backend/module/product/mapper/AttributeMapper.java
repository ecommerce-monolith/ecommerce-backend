package com.huyhn.ecommerce_backend.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Attribute;
import com.huyhn.ecommerce_backend.shared.mapper.EntityMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AttributeMapper extends EntityMapper<Attribute, AttributeDTO> {
}
