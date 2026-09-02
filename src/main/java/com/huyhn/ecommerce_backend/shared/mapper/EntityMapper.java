package com.huyhn.ecommerce_backend.shared.mapper;

import org.mapstruct.MappingTarget;

public interface EntityMapper<E, D> {
    E toEntity(D dto);

    D toDto(E entity);

    void partialUpdate(@MappingTarget E entity, D dto);
}
