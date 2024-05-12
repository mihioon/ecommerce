package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.object.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailJpaRepository extends JpaRepository<OrderProduct, Long> {
}
