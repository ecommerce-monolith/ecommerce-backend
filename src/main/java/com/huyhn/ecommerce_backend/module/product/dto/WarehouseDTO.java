package com.huyhn.ecommerce_backend.module.product.dto;

import java.util.UUID;

import com.huyhn.ecommerce_backend.shared.dto.AuditDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class WarehouseDTO extends AuditDTO {
    private UUID id;
    private String name;
    private String address;
    private String city;
    private String state;
}
