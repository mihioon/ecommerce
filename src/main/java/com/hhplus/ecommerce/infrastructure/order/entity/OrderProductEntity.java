package com.hhplus.ecommerce.infrastructure.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor
@Table(name = "order_product",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"orderId", "productId"})})
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private BigDecimal prodTotalPrice;

    public OrderProductEntity(String orderId, Long productId, Long quantity, BigDecimal prodTotalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.prodTotalPrice = prodTotalPrice;
    }
}
