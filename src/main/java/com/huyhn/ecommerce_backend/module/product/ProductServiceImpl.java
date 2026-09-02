package com.huyhn.ecommerce_backend.module.product;

import com.huyhn.ecommerce_backend.module.product.entity.Product;
import com.huyhn.ecommerce_backend.module.product.mapper.ProductItemMapper;
import com.huyhn.ecommerce_backend.module.product.repository.ProductRepository;
import com.huyhn.ecommerce_backend.module.product.request.ProductPageRequest;
import com.huyhn.ecommerce_backend.module.product.response.ProductPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductItemMapper productItemMapper;

    @Override
    public ProductPageResponse getPage(ProductPageRequest filter, Pageable pageable) {
        Page<Product> products = productRepository.findAllByFilter(filter, pageable);
        return new ProductPageResponse(
                products.map(productItemMapper::toDto).toList(),
                products.getTotalElements(),
                products.getTotalPages(),
                products.getNumber(),
                products.getSize()
        );
    }
}
