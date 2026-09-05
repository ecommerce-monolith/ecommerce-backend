package com.huyhn.ecommerce_backend.module.product.service;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.huyhn.ecommerce_backend.module.product.dto.WarehouseDTO;
import com.huyhn.ecommerce_backend.module.product.request.CreateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.WarehouseFilterRequest;
import com.huyhn.ecommerce_backend.module.product.response.WarehousePageResponse;

public interface WarehouseService {
    WarehousePageResponse getPage(WarehouseFilterRequest request, Pageable pageable);

    WarehouseDTO getById(UUID id);

    WarehouseDTO create(CreateWarehouseRequest request);

    WarehouseDTO update(UUID id, UpdateWarehouseRequest request);

    void delete(UUID id);
}
