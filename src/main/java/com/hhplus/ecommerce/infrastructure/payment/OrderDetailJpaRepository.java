package com.hhplus.ecommerce.infrastructure.payment;

import com.hhplus.ecommerce.business.payment.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailJpaRepository extends JpaRepository<OrderDetail, Long> {
}
