package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.object.OrderProduct;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailJpaRepository extends JpaRepository<OrderProductEntity, Long> {
}
