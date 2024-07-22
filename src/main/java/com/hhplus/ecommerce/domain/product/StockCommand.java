package com.hhplus.ecommerce.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StockCommand {
    private Long productId;
    private Long orderQuantity;

    // 재고 검증
    public void validate(Long currentQuantity){
        if (currentQuantity < orderQuantity) {
            throw new IllegalArgumentException("재고 부족 productId = " + productId);
        }
    }
}
