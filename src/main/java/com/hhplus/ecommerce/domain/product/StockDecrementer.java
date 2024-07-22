package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StockDecrementer {
    public final StockValidator stockValidator;
    public final ProductRepository productRepository;

    public void deductStock(StockCommand stock){
        Long productId = stock.getProductId();
        Long orderQuantity = stock.getOrderQuantity();

        // null 확인
        NullChecker.checkNotNull(stock, "StockCommand");

        // 상품 조회
        Product.Detail productDetail = productRepository.findProductDetailById(productId);

        // 재고 확인
        stockValidator.validateStock(productDetail, orderQuantity, productId);

        // 수량 감소 및 저장
        productRepository.minusQuantity(productId, orderQuantity);
    }
}
