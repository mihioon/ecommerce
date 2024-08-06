package com.hhplus.ecommerce.infrastructure.customer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_point_history",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"customerId", "dateTime"})})
public class PointHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Unique */
    @Column(nullable = false)
    private Long customerId;

    /* Unique */
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Type type;


    public PointHistoryEntity(Long customerId, BigDecimal point, Type type) {
        this.customerId = customerId;
        this.type = type;
        this.point = point;
        this.dateTime = LocalDateTime.now();
    }

    public enum Type {
        CHARGE,
        USE,
    }
}
