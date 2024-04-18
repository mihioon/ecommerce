package com.hhplus.ecommerce.business.customer.model;

import jakarta.persistence.*;
import lombok.Builder;

import java.math.BigDecimal;

@Entity
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

    public CustomerAmountHistory(Customer customer, String type, BigDecimal amount) {
        this.customer = customer;
        this.type = type;
        this.amount = amount;
    }
}
