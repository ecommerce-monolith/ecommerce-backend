package com.huyhn.ecommerce_backend.module.product.controller;

import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyhn.ecommerce_backend.module.product.dto.WarehouseDTO;
import com.huyhn.ecommerce_backend.module.product.request.CreateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateWarehouseRequest;
import com.huyhn.ecommerce_backend.module.product.request.WarehouseFilterRequest;
import com.huyhn.ecommerce_backend.module.product.response.WarehousePageResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Warehouse", description = "Warehouse API")
@RequestMapping("/api/v1/admin/warehouses")
public interface WarehouseController {

    @Operation(summary = "Get page of warehouses")
    @GetMapping
    ResponseEntity<WarehousePageResponse> getPage(@ParameterObject WarehouseFilterRequest request,
            @ParameterObject Pageable pageable);

    @Operation(summary = "Get warehouse by id")
    @GetMapping("/{id}")
    ResponseEntity<WarehouseDTO> getById(@PathVariable UUID id);

    @Operation(summary = "Create warehouse")
    @PostMapping
    ResponseEntity<WarehouseDTO> create(@RequestBody @Validated CreateWarehouseRequest request);

    @Operation(summary = "Update warehouse")
    @PutMapping("/{id}")
    ResponseEntity<WarehouseDTO> update(@PathVariable UUID id,
            @RequestBody @Validated UpdateWarehouseRequest request);

    @Operation(summary = "Delete warehouse")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable UUID id);
}
