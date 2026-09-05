package com.huyhn.ecommerce_backend.module.product.mapper;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Attribute;
import com.huyhn.ecommerce_backend.shared.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AttributeMapper extends EntityMapper<Attribute, AttributeDTO> {
}
