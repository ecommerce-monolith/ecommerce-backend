package com.huyhn.ecommerce_backend.module.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.huyhn.ecommerce_backend.module.product.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, UUID> {

}
