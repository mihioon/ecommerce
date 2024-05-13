package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.object.OrderHistory;
import com.hhplus.ecommerce.domain.order.object.OrderProduct;
import com.hhplus.ecommerce.domain.order.repository.OrderRepository;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderHistoryBuilder {
    private final OrderRepository orderRepository;

    public OrderHistory build(OrderEntity orderEntity, List<OrderProductEntity> orderProductEntities){
        OrderHistory orderHistory = new OrderHistory(orderEntity.getOrderId(),
                orderEntity.getCustomerId(),
                orderEntity.getOrderDate(),
                orderEntity.getOrderState(),
                orderEntity.getTotalPrice(),
                orderProductEntities.stream()
                        .map(entity -> new OrderProduct(
                                entity.getProductId(),
                                entity.getQuantity(),
                                entity.getProdTotalPrice()
                        )).collect(Collectors.toList())
        );
        return orderHistory;
    }
}
