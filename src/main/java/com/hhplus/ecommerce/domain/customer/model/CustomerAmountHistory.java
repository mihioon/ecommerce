package com.hhplus.ecommerce.domain.customer.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
@Builder
@Table(name = "customer_amount_history")
public class CustomerAmountHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal amount; /* 잔액 */
}
