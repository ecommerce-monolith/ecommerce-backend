package com.huyhn.ecommerce_backend.module.product.service.impl;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyhn.ecommerce_backend.exception.BusinessException;
import com.huyhn.ecommerce_backend.exception.ErrorCode;
import com.huyhn.ecommerce_backend.module.product.dto.WarehouseDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Warehouse;
import com.huyhn.ecommerce_backend.module.product.mapper.WarehouseMapper;
import com.huyhn.ecommerce_backend.module.product.repository.WarehouseRepository;
import com.huyhn.ecommerce_backend.module.product.request.CreateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.WarehouseFilterRequest;
import com.huyhn.ecommerce_backend.module.product.response.WarehousePageResponse;
import com.huyhn.ecommerce_backend.module.product.service.WarehouseService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;

    @Override
    public WarehousePageResponse getPage(WarehouseFilterRequest request, Pageable pageable) {
        Page<Warehouse> page = warehouseRepository.findAll(pageable);
        return WarehousePageResponse.builder()
                .items(page.getContent().stream().map(warehouseMapper::toDto).toList())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .currentPage(page.getNumber())
                .pageSize(page.getSize())
                .build();
    }

    @Override
    public WarehouseDTO getById(UUID id) {
        return warehouseMapper.toDto(warehouseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                        "entity", "Warehouse",
                        "id", id.toString()))));
    }

    @Override
    public WarehouseDTO create(CreateWarehouseRequest request) {
        Warehouse warehouse = warehouseMapper.toEntityForCreating(request);
        warehouseRepository.save(warehouse);
        return warehouseMapper.toDto(warehouse);
    }

    @Override
    public WarehouseDTO update(UUID id, UpdateWarehouseRequest request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                        "entity", "Warehouse",
                        "id", id.toString())));
        warehouseMapper.partialUpdate(warehouse, request);
        warehouseRepository.save(warehouse);
        return warehouseMapper.toDto(warehouse);
    }

    @Override
    public void delete(UUID id) {
        warehouseRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                        "entity", "Warehouse",
                        "id", id.toString())));
        warehouseRepository.deleteById(id);
    }

}
