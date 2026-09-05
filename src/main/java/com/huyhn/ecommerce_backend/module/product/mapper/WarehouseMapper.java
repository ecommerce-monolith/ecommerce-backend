package com.huyhn.ecommerce_backend.module.product.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.huyhn.ecommerce_backend.module.product.dto.WarehouseDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Warehouse;
import com.huyhn.ecommerce_backend.module.product.request.CreateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateWarehouseRequest;
import com.huyhn.ecommerce_backend.shared.mapper.EntityMapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WarehouseMapper extends EntityMapper<Warehouse, WarehouseDTO> {
    @Mapping(target = "id", ignore = true)
    Warehouse toEntityForCreating(CreateWarehouseRequest request);

    void partialUpdate(@MappingTarget Warehouse warehouse, UpdateWarehouseRequest request);
}
