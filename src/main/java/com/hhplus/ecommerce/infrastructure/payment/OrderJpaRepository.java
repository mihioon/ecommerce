package com.hhplus.ecommerce.infrastructure.payment;

import com.hhplus.ecommerce.business.payment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
