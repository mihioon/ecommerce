package com.hhplus.ecommerce.infrastructure.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicUpdate /* 변경된 필드만 포함하여 SQL 문을 생성 */
@Table(name = "customer")
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */

    public void updatePoint(BigDecimal point) {
        this.point = point;
    }
}
