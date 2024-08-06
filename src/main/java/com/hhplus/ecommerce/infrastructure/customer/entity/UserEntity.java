package com.hhplus.ecommerce.infrastructure.customer.entity;

import com.hhplus.ecommerce.support.exception.InputValidationException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */

    @Version
    private Long version; /* 낙관적 락 구현 */

    public UserEntity(Long id, BigDecimal point) {
        this.id = id;
        this.point = point;
    }

    public void plusPoint(BigDecimal point) {
        this.point = this.point.add(point);
    }

    public void minusPoint(BigDecimal point) {
        this.point = this.point.subtract(point);
    }

    public BigDecimal toPoint() {
        return this.point;
    }
}
