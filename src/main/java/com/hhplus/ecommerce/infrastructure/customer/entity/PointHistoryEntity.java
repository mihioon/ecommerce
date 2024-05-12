package com.hhplus.ecommerce.infrastructure.customer.entity;

import com.hhplus.ecommerce.domain.customer.object.PointType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "customer_point_history",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"customerId", "date"})})
public class PointHistoryEntity {
    /* Unique */
    @Column(nullable = false)
    private Long customerId;
    /* Unique */
    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PointType type;

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */
}
