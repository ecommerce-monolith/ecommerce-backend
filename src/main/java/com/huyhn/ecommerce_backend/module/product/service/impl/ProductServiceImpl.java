package com.huyhn.ecommerce_backend.module.product.service.impl;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huyhn.ecommerce_backend.module.product.dto.SkuDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Inventory;
import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.entity.Sku;
import com.huyhn.ecommerce_backend.module.product.enumeration.ProductStatus;
import com.huyhn.ecommerce_backend.module.product.mapper.CreateProductMapper;
import com.huyhn.ecommerce_backend.module.product.mapper.ProductItemMapper;
import com.huyhn.ecommerce_backend.module.product.repository.InventoryRepository;
import com.huyhn.ecommerce_backend.module.product.repository.ProductRepository;
import com.huyhn.ecommerce_backend.module.product.repository.SkuRepository;
import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import com.huyhn.ecommerce_backend.module.product.service.ProductService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final SkuRepository skuRepository;

    private final ProductItemMapper productItemMapper;
    private final CreateProductMapper createProductMapper;

    @Override
    public ProductPageResponse getPage(ProductPageRequest filter, Pageable pageable) {
        Page<Product> products = productRepository.findAllByFilter(filter, pageable);
        return ProductPageResponse.builder()
                .items(products.getContent().stream().map(productItemMapper::toDto).toList())
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .currentPage(products.getNumber())
                .build();
    }

    @Override
    public ProductCreatedResponse createProduct(CreateProductRequest request) {
        Product created = productRepository.save(createProductMapper.toEntity(request));

        for (SkuDTO sku : request.skus()) {
            Sku skuEntity = skuRepository.save(Sku.builder()
                    .productId(created.getId())
                    .skuCode(UUID.randomUUID().toString())
                    .barCode(UUID.randomUUID().toString())
                    .price(sku.price())
                    .status(ProductStatus.ACTIVE.name())
                    .build());

            inventoryRepository.save(Inventory.builder()
                    .skuId(skuEntity.getId())
                    .quantity(sku.quantity())
                    .warehouseId(sku.warehouseId())
                    .build());
        }

        return new ProductCreatedResponse(created.getId());
    }
}
