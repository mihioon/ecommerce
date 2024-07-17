package com.hhplus.ecommerce.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;

    // 주문 아이디로 주문 조회
    public Order read(Long orderId){
        return orderRepository.findOrder(orderId);
    }
}
