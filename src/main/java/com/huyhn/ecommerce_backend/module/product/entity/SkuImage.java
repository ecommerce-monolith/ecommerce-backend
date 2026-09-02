package com.huyhn.ecommerce_backend.module.product.entity;

import com.huyhn.ecommerce_backend.shared.entity.AbstractAuditingEntity;
import com.huyhn.ecommerce_backend.shared.uuidv7.GeneratedUuidV7;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "sku_images")
@Getter
@Setter
@NoArgsConstructor
public class SkuImage extends AbstractAuditingEntity {

    @Id
    @GeneratedUuidV7
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "sku_id", columnDefinition = "BINARY(16)")
    private UUID skuId;
}
