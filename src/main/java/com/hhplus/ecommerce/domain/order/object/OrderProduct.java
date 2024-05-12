package com.hhplus.ecommerce.domain.order.object;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OrderProduct {
    private Long productId;
    private Long quantity;
    private BigDecimal prodTotalPrice;
}
