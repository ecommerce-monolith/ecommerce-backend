package com.huyhn.ecommerce_backend.module.product.response;

import com.huyhn.ecommerce_backend.module.product.dto.AttributeDTO;
import com.huyhn.ecommerce_backend.shared.response.PageResponse;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AttributePageResponse extends PageResponse<AttributeDTO> {
}
