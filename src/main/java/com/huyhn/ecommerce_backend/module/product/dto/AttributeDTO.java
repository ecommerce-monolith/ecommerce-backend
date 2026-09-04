package com.huyhn.ecommerce_backend.module.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AttributeDTO {
    private String name;
    private List<AttributeValueItem> values;
}
