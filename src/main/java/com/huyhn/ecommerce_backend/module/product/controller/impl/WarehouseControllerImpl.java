package com.huyhn.ecommerce_backend.module.product.controller.impl;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.huyhn.ecommerce_backend.module.product.controller.WarehouseController;
import com.huyhn.ecommerce_backend.module.product.dto.WarehouseDTO;
import com.huyhn.ecommerce_backend.module.product.request.CreateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.WarehouseFilterRequest;
import com.huyhn.ecommerce_backend.module.product.response.WarehousePageResponse;
import com.huyhn.ecommerce_backend.module.product.service.WarehouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class WarehouseControllerImpl implements WarehouseController {
    private final WarehouseService warehouseService;

    @Override
    public ResponseEntity<WarehousePageResponse> getPage(WarehouseFilterRequest request, Pageable pageable) {
        WarehousePageResponse response = warehouseService.getPage(request, pageable);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WarehouseDTO> getById(UUID id) {
        WarehouseDTO response = warehouseService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WarehouseDTO> create(CreateWarehouseRequest request) {
        WarehouseDTO response = warehouseService.create(request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<WarehouseDTO> update(UUID id, UpdateWarehouseRequest request) {
        WarehouseDTO response = warehouseService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> delete(UUID id) {
        warehouseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
