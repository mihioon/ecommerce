package com.hhplus.ecommerce.domain.order.repository;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface OrderProductRepository {
    public void saveAll(List<OrderProductEntity> orderProducts);
    public List<OrderProductEntity> findOrderProducts(String orderId);
}
