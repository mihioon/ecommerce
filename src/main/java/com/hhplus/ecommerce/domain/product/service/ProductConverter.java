package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.product.object.Product;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product toProduct(ProductEntity productEntity){
        return new Product(
                productEntity.getId(),
                productEntity.getProductNm(),
                productEntity.getSalePrice(),
                productEntity.getStockQuantity()
        );
    }
}
