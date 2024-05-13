package com.hhplus.ecommerce.infrastructure.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicUpdate
@AllArgsConstructor
@Table(name = "order_product",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"orderId", "productId"})})
public class OrderProductEntity {
    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal prodTotalPrice;
}
