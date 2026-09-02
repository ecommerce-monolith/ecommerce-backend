package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.Sku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkuRepository extends JpaRepository<Sku, UUID> {
}
