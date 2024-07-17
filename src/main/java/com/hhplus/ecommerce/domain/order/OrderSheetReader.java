package com.hhplus.ecommerce.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSheetReader {
    private final OrderRepository orderRepository;
    public OrderSheet read(Long orderSheetId) {
        return orderRepository.findOrderSheet(orderSheetId);
    }
}
