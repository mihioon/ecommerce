package com.hhplus.ecommerce.domain.product.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class Product {
    private Long id; /* Key */
    private String productNm; /* 제품명 */
    private BigDecimal salePrice; /* 제품금액 */
    private Long stockQuantity; /* 제품수량 */

    public void deduct(Long quantity){
        if (quantity.compareTo(this.stockQuantity) > 0) {
            throw new IllegalArgumentException("주문수량은 현재 재고보다 작거나 같아야합니다.");
        }
        this.stockQuantity = this.stockQuantity - quantity;
    }
}
