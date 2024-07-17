package com.hhplus.ecommerce.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderAppender {
    public final OrderRepository orderRepository;

    // 주문 생성
    public void append(Order order) {
        orderRepository.persistOrder(order); // 변환된 엔터티
        orderRepository.persistOrderProducts(order.getOrderProducts());
    }
}
