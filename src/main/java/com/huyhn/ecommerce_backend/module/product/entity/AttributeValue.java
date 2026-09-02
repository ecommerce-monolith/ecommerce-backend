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
@Table(name = "attribute_values")
@Getter
@Setter
@NoArgsConstructor
public class AttributeValue extends AbstractAuditingEntity {
    @Id
    @GeneratedUuidV7
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String value;

    @Column(name = "attribute_id", columnDefinition = "BINARY(16)")
    private UUID attributeId;
}
