package com.huyhn.ecommerce_backend.module.product.entity;

import com.huyhn.ecommerce_backend.shared.entity.AbstractAuditingEntity;
import com.huyhn.ecommerce_backend.shared.uuidv7.GeneratedUuidV7;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Product extends AbstractAuditingEntity {
    @Id
    @GeneratedUuidV7
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "category_id", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @Column(name = "brand_id", columnDefinition = "BINARY(16)")
    private UUID brandId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private Set<Sku> skus;
}
