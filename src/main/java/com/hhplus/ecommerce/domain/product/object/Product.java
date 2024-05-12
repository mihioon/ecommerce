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
}
