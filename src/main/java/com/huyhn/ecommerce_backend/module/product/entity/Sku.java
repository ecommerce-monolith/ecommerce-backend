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

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "skus")
@Getter
@Setter
@NoArgsConstructor
public class Sku extends AbstractAuditingEntity {
    @Id
    @GeneratedUuidV7
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "product_id", columnDefinition = "BINARY(16)")
    private UUID productId;

    @Column(name = "sku_code", unique = true)
    private String skuCode;

    @Column(name = "barcode")
    private String barCode;

    @Column(name = "price", precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "status")
    private String status;
}
