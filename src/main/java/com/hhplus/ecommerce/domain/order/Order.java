package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private String orderId; /* Key */
    private Long customerId;
    private OrderState orderState; /* 주문 상태 */
    private BigDecimal totalPrice; /* 총 주문금액 */

    private List<OrderProduct> orderProducts;

    public Order(Long customerId, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.totalPrice = totalPrice;
    }

    public Order(String orderId, Long customerId, OrderState orderState, BigDecimal totalPrice) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
    }
}
