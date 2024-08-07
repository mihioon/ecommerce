package com.hhplus.ecommerce.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class OrderHistory {
    private String orderId; /* Key */
    private Long customerId;
    private LocalDateTime dateTime; /* 주문 날짜 */
    private Order.State orderState; /* 주문 상태 */
    private BigDecimal totalPrice; /* 총 주문금액 */
    private List<OrderProduct> orderDetails;
}
