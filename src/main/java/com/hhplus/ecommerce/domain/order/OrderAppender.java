package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderAppender {
    public final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    // 주문 생성
    public Long append(Order order) {
        //주문생성 후 주문 아이디 조회
        Long orderId = orderRepository.persistOrder(order);
        List<OrderProduct> orderProducts = order.getOrderProducts();

        //유효성 체크
        NullChecker.checkNotNull(orderProducts, "orderProducts");

        //주문 제품 리스트 저장
        orderRepository.persistOrderProducts(orderId, order.getOrderProducts());

        return orderId;
    }
}
