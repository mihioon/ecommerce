package com.hhplus.ecommerce.infrastructure.customer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;

@Entity
@Getter
@DynamicUpdate /* 변경된 필드만 포함하여 SQL 문을 생성 */
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private BigDecimal point; /* 잔액 */

    @Version
    private Long version; /* 낙관적 락 구현 */

    public CustomerEntity(BigDecimal point) {
        this.point = point;
    }

    public CustomerEntity(Long id, BigDecimal point) {
        this.id = id;
        this.point = point;
    }

    public void updatePoint(BigDecimal point) {
        this.point = point;
    }
}
