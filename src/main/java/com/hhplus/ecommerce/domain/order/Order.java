package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.aspectj.weaver.ast.Or;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class Order {
    private Long id; /* Key */
    private String orderCode;
    private Long customerId;
    private OrderState orderState; /* 주문 상태 */
    private BigDecimal totalPrice; /* 총 주문금액 */

    private List<OrderProduct> orderProducts;

    public Order(String orderCode, Long customerId, BigDecimal totalPrice) {
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.orderState = OrderState.NEW;
    }

    public Order(Long id, String orderCode, Long customerId, OrderState orderState, BigDecimal totalPrice) {
        this.id = id;
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
    }
}
