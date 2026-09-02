package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.SkuImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkuImageRepository extends JpaRepository<SkuImage, UUID> {
}
