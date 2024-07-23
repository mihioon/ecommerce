package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockManager {
    public final ProductRepository productRepository;

    public void deductStock(StockCommand stock){
        handleStockOperation(stock, -1);
    }

    public void addStock(StockCommand stock) {
        handleStockOperation(stock, 1);
    }

    private void handleStockOperation(StockCommand stock, int sign) {
        NullChecker.checkNotNull(stock, "stock");
        Long productId = stock.getProductId();
        Long orderQuantity = stock.getOrderQuantity();

        // 현재 재고 조회
        NullChecker.checkNotNull(productId, "productId");
        Long currentQuantity = productRepository.findStockById(productId);
        log.info("[currentQuantity] " + currentQuantity);
        // 값 검증
        stock.validateValue(currentQuantity);
        if(sign == -1){ // deductStock
            stock.validateForDeduction(currentQuantity);
        }

        // 수량 변경 및 저장
        Long newQuantity = currentQuantity + (orderQuantity * sign);
        productRepository.updateQuantity(productId, newQuantity);
    }
}
