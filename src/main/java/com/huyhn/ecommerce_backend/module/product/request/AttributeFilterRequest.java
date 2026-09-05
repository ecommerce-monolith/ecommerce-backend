package com.huyhn.ecommerce_backend.module.product.request;

import com.huyhn.ecommerce_backend.shared.request.BaseFilterRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AttributeFilterRequest extends BaseFilterRequest {
}
