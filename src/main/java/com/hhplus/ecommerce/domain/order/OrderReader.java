package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;

    public OrderEntity read(String orderId){
        return orderRepository.findOrder(orderId);
    }
}
