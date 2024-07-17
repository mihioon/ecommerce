package com.hhplus.ecommerce.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductJpaRepository extends JpaRepository<OrderProductEntity, Long> {
    List<OrderProductEntity> findByOrderId(String orderId);
}
