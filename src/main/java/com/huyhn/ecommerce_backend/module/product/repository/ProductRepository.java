package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("""
            SELECT p FROM Product p
            WHERE (:#{#filter.name} IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#filter.name}, '%')))
            """)
    Page<Product> findAllByFilter(@Param("filter") ProductPageRequest filter, Pageable pageable);
}
