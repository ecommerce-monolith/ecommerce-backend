package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
    List<Inventory> findBySkuId(UUID skuId);

    List<Inventory> findBySkuIdIn(Collection<UUID> skuIds);
}
