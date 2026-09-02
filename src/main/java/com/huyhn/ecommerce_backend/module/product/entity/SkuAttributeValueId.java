package com.huyhn.ecommerce_backend.module.product.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class SkuAttributeValueId {

    private UUID skuId;

    private UUID attributeValueId;

    public SkuAttributeValueId(UUID skuId, UUID attributeValueId) {
        this.skuId = skuId;
        this.attributeValueId = attributeValueId;
    }
}
