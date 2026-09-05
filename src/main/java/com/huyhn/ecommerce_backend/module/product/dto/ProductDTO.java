package com.huyhn.ecommerce_backend.module.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private String description;
}
