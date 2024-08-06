package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private String orderCode;

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private LocalDateTime orderDate; /* 주문 날짜 */

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Order.State orderState; /* 주문 상태 */

    @Column(nullable = false)
    private BigDecimal totalPrice; /* 총 주문금액 */

    public OrderEntity(String orderCode, Long customerId, LocalDateTime now, Order.State orderState, BigDecimal totalPrice) {
        this.orderCode = orderCode;
        this.customerId = customerId;
        this.orderDate = now;
        this.orderState = orderState;
        this.totalPrice = totalPrice;
    }
}