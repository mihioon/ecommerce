package com.hhplus.ecommerce.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderSheet {
    private String id; /* PK : auto increment */
    private Long customerId;
    private BigDecimal totalAmount;
    // 주소, 결제타입 etc.

    private List<OrderProduct> orderProducts;
}
