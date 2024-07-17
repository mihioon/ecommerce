package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.util.OrderState;
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
    private String orderId; /* Key */

    @Column(nullable = false)
    private Long customerId;

    @Column(nullable = false)
    private LocalDateTime orderDate; /* 주문 날짜 */

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderState orderState; /* 주문 상태 */

    @Column(nullable = false)
    private BigDecimal totalPrice; /* 총 주문금액 */

    public OrderEntity(Long customerId, BigDecimal totalPrice) {
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.orderState = OrderState.NEW;
        this.totalPrice = totalPrice;
    }
}