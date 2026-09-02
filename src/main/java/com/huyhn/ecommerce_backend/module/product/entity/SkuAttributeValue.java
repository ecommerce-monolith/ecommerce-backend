package com.huyhn.ecommerce_backend.module.product.entity;

import com.huyhn.ecommerce_backend.shared.entity.AbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sku_attribute_values")
@Getter
@Setter
@NoArgsConstructor
public class SkuAttributeValue extends AbstractAuditingEntity {
    @EmbeddedId
    private SkuAttributeValueId id;

    @MapsId("skuId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sku_id", columnDefinition = "BINARY(16)")
    private Sku skuId;

    @MapsId("attributeValueId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attribute_value_id", columnDefinition = "BINARY(16)")
    private AttributeValue attributeValueId;
}
