package com.hhplus.ecommerce.infrastructure.customer.entity;

import com.hhplus.ecommerce.util.PointType;
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
    @Enumerated(EnumType.STRING)
    private PointType type;

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */

    public PointHistoryEntity(Long customerId, LocalDateTime dateTime, PointType type, BigDecimal point) {
        this.customerId = customerId;
        this.dateTime = dateTime;
        this.type = type;
        this.point = point;
    }
}
