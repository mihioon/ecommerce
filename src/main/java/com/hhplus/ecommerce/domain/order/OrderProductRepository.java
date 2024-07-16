package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;

import java.util.List;

public interface OrderProductRepository {
    public void saveAll(List<OrderProductEntity> orderProducts);
    public List<OrderProductEntity> findOrderProducts(String orderId);
}
