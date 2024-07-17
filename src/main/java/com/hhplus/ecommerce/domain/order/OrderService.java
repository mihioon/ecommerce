package com.hhplus.ecommerce.domain.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderReader orderReader;
    private final OrderSheetReader orderSheetReader;
    private final OrderAppender orderAppender;

    // 주문 아이디로 주문 조회
    public Order findOrder(Long orderId){
        return orderReader.read(orderId);
    }

    // 주문 생성(주문 상세 포함)
    public Long createOrder(Order order) {
        return orderAppender.append(order);
    }

    // 주문서 아이디로 주문서 조회
    public OrderSheet findOrderSheet(Long orderSheetId){
        return orderSheetReader.read(orderSheetId);
    }

    // 주문서 객체 > 주문 객체 변환
    public Order toOrder(OrderSheet orderSheet){
        return new Order(
                orderSheet.getId(),
                orderSheet.getCustomerId(),
                orderSheet.getTotalAmount()
        );
    }
}
