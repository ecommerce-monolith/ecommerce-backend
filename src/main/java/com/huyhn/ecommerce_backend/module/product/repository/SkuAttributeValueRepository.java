package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValue;
import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValueId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkuAttributeValueRepository extends JpaRepository<SkuAttributeValue, SkuAttributeValueId> {
}
