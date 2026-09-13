package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AttributeRepository extends JpaRepository<Attribute, UUID> {
    Optional<Attribute> findFirstByNameIgnoreCase(String name);
}
