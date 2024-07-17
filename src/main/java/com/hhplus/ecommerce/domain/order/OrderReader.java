package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderReader {
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    // 주문 아이디로 주문 조회
    public Order read(Long orderId){
        Order order = orderRepository.findOrder(orderId);
        NullChecker.checkNotNull(order, "order");
        return order;
    }
}
