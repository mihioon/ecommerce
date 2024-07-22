package com.hhplus.ecommerce.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockIncrementer {
    public final ProductRepository productRepository;

    public void addStock(StockCommand stock) {
        Long productId = stock.getProductId();
        Long orderQuantity = stock.getOrderQuantity();

        // 수량 증가 및 저장
        productRepository.plusQuantity(productId, orderQuantity);
    }
}
