package com.hhplus.ecommerce.domain.customer.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@DynamicUpdate
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private BigDecimal amount; /* 잔액 */

    @OneToMany(mappedBy = "customer")
    private List<CustomerAmountHistory> customerAmountHistories;
}
