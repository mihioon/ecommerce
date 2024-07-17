package com.hhplus.ecommerce.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderProduct {
    private Long orderId;
    private Long productId;
    private BigDecimal productAmount;
    private BigDecimal finalPrice;
    private BigDecimal discountRate;
    private Long quantity;

    public OrderProduct(Long productId, Long quantity, BigDecimal prodTotalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.productAmount = prodTotalPrice;
    }

    public OrderProduct(Long productId, BigDecimal finalPrice, Long quantity) {
        this.productId = productId;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
    }
}
