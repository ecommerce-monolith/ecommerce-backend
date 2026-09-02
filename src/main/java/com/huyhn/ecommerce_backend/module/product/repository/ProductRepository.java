package com.huyhn.ecommerce_backend.module.product.repository;

import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    default Page<Product> findAllByFilter(ProductPageRequest filter, Pageable pageable) {
        return findAllByFilterQuery(filter.name(), filter.minPrice(), filter.maxPrice(), pageable);
    }

    @Query("""
            SELECT p FROM Product p
            WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')))
                AND (
                    (:minPrice IS NULL AND :maxPrice IS NULL)
                    OR EXISTS (
                        SELECT 1 FROM Sku s
                        WHERE s.product = p
                            AND (:minPrice IS NULL OR s.price >= :minPrice)
                            AND (:maxPrice IS NULL OR s.price <= :maxPrice)
                    )
                )
            """)
    Page<Product> findAllByFilterQuery(@Param("name") String name,
                                       @Param("minPrice") BigDecimal minPrice,
                                       @Param("maxPrice") BigDecimal maxPrice,
                                       Pageable pageable);

}
