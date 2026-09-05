package com.huyhn.ecommerce_backend.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.shared.mapper.EntityMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreateProductMapper extends EntityMapper<Product, CreateProductRequest> {
}
