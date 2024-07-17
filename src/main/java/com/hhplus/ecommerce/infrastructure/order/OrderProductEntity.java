package com.hhplus.ecommerce.infrastructure.order;

import jakarta.persistence.*;
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
    private Long orderId;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private BigDecimal finalPrice;

    @Column
    private BigDecimal discountRate;

    @Column(nullable = false)
    private Long quantity;


    public OrderProductEntity(Long orderId, Long productId, Long quantity, BigDecimal finalPrice) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }

    public OrderProductEntity(Long productId, Long quantity, BigDecimal finalPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
    }
}
