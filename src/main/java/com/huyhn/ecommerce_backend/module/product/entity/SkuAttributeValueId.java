package com.huyhn.ecommerce_backend.module.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class SkuAttributeValueId {

    @Column(name = "sku_id", columnDefinition = "BINARY(16)")
    private UUID skuId;

    @Column(name = "attribute_value_id", columnDefinition = "BINARY(16)")
    private UUID attributeValueId;

    public SkuAttributeValueId(UUID skuId, UUID attributeValueId) {
        this.skuId = skuId;
        this.attributeValueId = attributeValueId;
    }
}
