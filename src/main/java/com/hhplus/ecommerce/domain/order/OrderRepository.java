package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;

public interface OrderRepository {
    public void save(OrderEntity order);
    public OrderEntity findOrder(String orderId);
}
