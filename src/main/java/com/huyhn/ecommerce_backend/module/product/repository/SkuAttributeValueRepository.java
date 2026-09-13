package com.huyhn.ecommerce_backend.module.product.repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValue;
import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValueId;

public interface SkuAttributeValueRepository extends JpaRepository<SkuAttributeValue, SkuAttributeValueId> {

    @Query("""
            select sav from SkuAttributeValue sav
            join fetch sav.attributeValueId
            where sav.id.skuId in :skuIds
            """)
    List<SkuAttributeValue> findWithValuesBySkuIds(@Param("skuIds") Collection<UUID> skuIds);

    @Modifying(flushAutomatically = true, clearAutomatically = false)
    @Query("delete from SkuAttributeValue sav where sav.id.skuId = :skuId")
    void deleteBySkuId(@Param("skuId") UUID skuId);
}
