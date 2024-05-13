package com.hhplus.ecommerce.infrastructure.order.entity;

import com.hhplus.ecommerce.util.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicUpdate
@AllArgsConstructor
@Table(name = "orders")
public class OrderEntity {
    @Id
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
}