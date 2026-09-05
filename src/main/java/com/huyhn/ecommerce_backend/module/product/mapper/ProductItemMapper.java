package com.huyhn.ecommerce_backend.module.product.mapper;

import com.huyhn.ecommerce_backend.module.product.dto.ProductDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.shared.mapper.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductItemMapper extends EntityMapper<Product, ProductDTO> {
}
