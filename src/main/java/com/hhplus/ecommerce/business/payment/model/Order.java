package com.hhplus.ecommerce.business.payment.model;

import com.hhplus.ecommerce.business.customer.model.Customer;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@DynamicUpdate
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private String orderState; /* 주문 상태 */

    @Column(nullable = false)
    private BigDecimal totalPrice; /* 총 주문금액 */

    @Column(nullable = false)
    private Date orderDate; /* 주문 날짜 */

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date modDate;
}
