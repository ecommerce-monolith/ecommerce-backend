package com.huyhn.ecommerce_backend.module.product.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.huyhn.ecommerce_backend.exception.BusinessException;
import com.huyhn.ecommerce_backend.exception.ErrorCode;
import com.huyhn.ecommerce_backend.module.product.dto.AttributeValueDTO;
import com.huyhn.ecommerce_backend.module.product.dto.SkuDTO;
import com.huyhn.ecommerce_backend.module.product.entity.Attribute;
import com.huyhn.ecommerce_backend.module.product.entity.AttributeValue;
import com.huyhn.ecommerce_backend.module.product.entity.Inventory;
import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.entity.Sku;
import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValue;
import com.huyhn.ecommerce_backend.module.product.entity.SkuAttributeValueId;
import com.huyhn.ecommerce_backend.module.product.enumeration.ProductStatus;
import com.huyhn.ecommerce_backend.module.product.mapper.CreateProductMapper;
import com.huyhn.ecommerce_backend.module.product.mapper.ProductItemMapper;
import com.huyhn.ecommerce_backend.module.product.repository.AttributeRepository;
import com.huyhn.ecommerce_backend.module.product.repository.AttributeValueRepository;
import com.huyhn.ecommerce_backend.module.product.repository.InventoryRepository;
import com.huyhn.ecommerce_backend.module.product.repository.ProductRepository;
import com.huyhn.ecommerce_backend.module.product.repository.SkuAttributeValueRepository;
import com.huyhn.ecommerce_backend.module.product.repository.SkuRepository;
import com.huyhn.ecommerce_backend.module.product.request.CreateProductRequest;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.request.UpdateProductRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductCreatedResponse;
import com.huyhn.ecommerce_backend.module.product.response.ProductDetailResponse;
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
    private final AttributeRepository attributeRepository;
    private final AttributeValueRepository attributeValueRepository;
    private final SkuAttributeValueRepository skuAttributeValueRepository;

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
        List<SkuDTO> skus = request.skus() == null ? List.of() : request.skus();
        skus.forEach(sku -> persistSku(created.getId(), sku));
        return new ProductCreatedResponse(created.getId());
    }

    @Override
    public ProductDetailResponse getById(UUID id) {
        return toDetail(requireProduct(id));
    }

    @Override
    public ProductDetailResponse updateProduct(UUID id, UpdateProductRequest request) {
        Product product = requireProduct(id);
        product.setName(request.name());
        productRepository.save(product);

        List<SkuDTO> skus = request.skus() == null ? List.of() : request.skus();
        Set<UUID> keptIds = skus.stream()
                .map(SkuDTO::id)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        for (Sku existing : skuRepository.findByProductId(id)) {
            if (!keptIds.contains(existing.getId())) {
                skuAttributeValueRepository.deleteBySkuId(existing.getId());
                skuAttributeValueRepository.flush();
                inventoryRepository.deleteAll(inventoryRepository.findBySkuId(existing.getId()));
                skuRepository.delete(existing);
            }
        }

        skus.forEach(sku -> persistSku(id, sku));
        return toDetail(product);
    }

    private Product requireProduct(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                        "entity", "Product",
                        "id", id.toString())));
    }

    private ProductDetailResponse toDetail(Product product) {
        List<Sku> skus = skuRepository.findByProductId(product.getId());
        List<UUID> skuIds = skus.stream().map(Sku::getId).toList();
        Map<UUID, Inventory> inventories = skuIds.isEmpty()
                ? Map.of()
                : inventoryRepository.findBySkuIdIn(skuIds).stream()
                        .collect(Collectors.toMap(Inventory::getSkuId, Function.identity(), (first, ignored) -> first));
        Map<UUID, List<AttributeValueDTO>> attributesBySkuId = loadSkuAttributes(skuIds);

        return new ProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                skus.stream()
                        .map(sku -> toSkuDto(sku, inventories.get(sku.getId()),
                                attributesBySkuId.getOrDefault(sku.getId(), List.of())))
                        .toList());
    }

    private Map<UUID, List<AttributeValueDTO>> loadSkuAttributes(List<UUID> skuIds) {
        if (skuIds.isEmpty()) {
            return Map.of();
        }

        List<SkuAttributeValue> links = skuAttributeValueRepository.findWithValuesBySkuIds(skuIds);
        Set<UUID> attributeIds = links.stream()
                .map(link -> link.getAttributeValueId().getAttributeId())
                .collect(Collectors.toSet());
        Map<UUID, Attribute> attributes = attributeIds.isEmpty()
                ? Map.of()
                : attributeRepository.findAllById(attributeIds).stream()
                        .collect(Collectors.toMap(Attribute::getId, Function.identity()));

        return links.stream().collect(Collectors.groupingBy(
                link -> link.getId().getSkuId(),
                Collectors.mapping(link -> {
                    AttributeValue value = link.getAttributeValueId();
                    Attribute attribute = attributes.get(value.getAttributeId());
                    return new AttributeValueDTO(
                            value.getId(),
                            value.getValue(),
                            attribute == null ? null : attribute.getName());
                }, Collectors.toList())));
    }

    private SkuDTO toSkuDto(Sku sku, Inventory inventory, List<AttributeValueDTO> attributeValues) {
        return new SkuDTO(
                sku.getId(),
                sku.getSkuCode(),
                sku.getPrice(),
                inventory == null ? null : inventory.getQuantity(),
                null,
                inventory == null ? null : inventory.getWarehouseId(),
                attributeValues);
    }

    private void persistSku(UUID productId, SkuDTO sku) {
        Sku entity;
        if (sku.id() != null) {
            entity = skuRepository.findById(sku.id())
                    .filter(existing -> productId.equals(existing.getProductId()))
                    .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                            "entity", "Sku",
                            "id", sku.id().toString())));
            entity.setSkuCode(skuCodeFrom(sku.name()));
            entity.setPrice(sku.price());
        } else {
            entity = Sku.builder()
                    .productId(productId)
                    .skuCode(skuCodeFrom(sku.name()))
                    .barCode(UUID.randomUUID().toString())
                    .price(sku.price())
                    .status(ProductStatus.ACTIVE.name())
                    .build();
        }

        entity = skuRepository.save(entity);
        upsertInventory(entity.getId(), sku);
        replaceSkuAttributes(entity, sku.attributeValues());
    }

    private void replaceSkuAttributes(Sku sku, List<AttributeValueDTO> values) {
        skuAttributeValueRepository.deleteBySkuId(sku.getId());
        skuAttributeValueRepository.flush();
        if (values == null || values.isEmpty()) {
            return;
        }

        for (AttributeValueDTO dto : values) {
            AttributeValue value = resolveAttributeValue(dto);
            if (value == null) {
                continue;
            }

            SkuAttributeValue link = new SkuAttributeValue();
            link.setId(new SkuAttributeValueId(sku.getId(), value.getId()));
            link.setSkuId(sku);
            link.setAttributeValueId(value);
            skuAttributeValueRepository.save(link);
        }
    }

    private AttributeValue resolveAttributeValue(AttributeValueDTO dto) {
        if (dto.id() != null) {
            return attributeValueRepository.findById(dto.id())
                    .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND, Map.of(
                            "entity", "AttributeValue",
                            "id", dto.id().toString())));
        }

        String valueName = dto.name() == null ? "" : dto.name().trim();
        if (valueName.isBlank()) {
            return null;
        }

        String attributeName = dto.attributeName() == null || dto.attributeName().isBlank()
                ? "Attribute"
                : dto.attributeName().trim();

        Attribute attribute = attributeRepository.findFirstByNameIgnoreCase(attributeName)
                .orElseGet(() -> {
                    Attribute created = new Attribute();
                    created.setName(attributeName);
                    return attributeRepository.save(created);
                });

        return attributeValueRepository.findFirstByAttributeIdAndValueIgnoreCase(attribute.getId(), valueName)
                .orElseGet(() -> {
                    AttributeValue created = new AttributeValue();
                    created.setAttributeId(attribute.getId());
                    created.setValue(valueName);
                    return attributeValueRepository.save(created);
                });
    }

    private void upsertInventory(UUID skuId, SkuDTO sku) {
        List<Inventory> existing = inventoryRepository.findBySkuId(skuId);
        if (existing.isEmpty()) {
            inventoryRepository.save(Inventory.builder()
                    .skuId(skuId)
                    .quantity(sku.quantity())
                    .warehouseId(sku.warehouseId())
                    .build());
            return;
        }

        Inventory inventory = existing.get(0);
        inventory.setQuantity(sku.quantity());
        inventory.setWarehouseId(sku.warehouseId());
        inventoryRepository.save(inventory);
    }

    private String skuCodeFrom(String name) {
        if (name == null || name.isBlank()) {
            return "SKU-" + UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        }

        String code = name.trim();
        return code.length() > 100 ? code.substring(0, 100) : code;
    }
}
