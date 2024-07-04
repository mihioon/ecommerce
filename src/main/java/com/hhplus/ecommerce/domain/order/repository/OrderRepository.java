package com.hhplus.ecommerce.domain.order.repository;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import org.springframework.stereotype.Repository;

public interface OrderRepository {
    public void save(OrderEntity order);
    public OrderEntity findOrder(String orderId);
}
