package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeValueRepository extends JpaRepository<AttributeValue, UUID> {
}
