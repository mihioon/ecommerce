package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductReader {
    private final ProductJpaRepository productRepository;

    /*public Product read(Long productId){
        ProductEntity entity = productRepository.findProductByProductId(productId);
        return new Product(entity.getId(), entity.getProductNm(), entity.getSalePrice(), entity.getStockQuantity());
    }*/

    public ProductEntity entityRead(Long productId){
        ProductEntity entity = productRepository.findProductById(productId);
        return entity;
    }


}
