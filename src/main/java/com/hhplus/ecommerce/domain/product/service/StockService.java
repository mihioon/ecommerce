package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.product.object.Product;
import com.hhplus.ecommerce.domain.product.object.ProductQuantity;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {
    private final ProductReader productReader;
    private final ProductConverter productConverter;

    public void deductStock(List<ProductQuantity> productQuantities){
        productQuantities.forEach(productQuantity -> {
            ProductEntity productEntity = productReader.entityRead(productQuantity.getId());
            Product product = productConverter.toProduct(productEntity);

            product.deduct(productQuantity.getQuantity());

            productEntity.updateQuantity(product.getStockQuantity());
        });
    }
}
