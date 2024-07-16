package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.util.OrderState;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Order {
    private String orderId; /* Key */
    private Long customerId;
    private LocalDateTime dateTime; /* 주문 날짜 */
    private OrderState orderState; /* 주문 상태 */
    private BigDecimal totalPrice; /* 총 주문금액 */
}
