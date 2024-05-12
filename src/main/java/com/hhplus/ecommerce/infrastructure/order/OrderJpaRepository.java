package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, String> {
}
