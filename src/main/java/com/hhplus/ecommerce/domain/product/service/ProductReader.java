package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.order.repository.OrderRepository;
import com.hhplus.ecommerce.domain.product.object.Product;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
