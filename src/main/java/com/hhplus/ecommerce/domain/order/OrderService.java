package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderSheetService orderSheetService;
    private final OrderAppender orderAppender;
    private final OrderRepository orderRepository;

    // 주문 아이디로 주문 조회
    public Order findOrder(Long orderId){
        Order order = orderRepository.findOrder(orderId);
        NullChecker.checkNotNull(order, "order");
        return order;
    }

    // 주문 생성(주문 상세 포함)
    public Long createOrder(Order order) {
        return orderAppender.append(order);
    }

    // 주문서 아이디로 주문 생성
    public Long createOrder(Long orderSheetId) {
        OrderSheet orderSheet = findOrderSheet(orderSheetId);
        Order order = toOrder(orderSheet);
        return createOrder(order);
    }

    // 주문 상태 업데이트
    public void updateState(Order.State state){

    }

    // 주문서 아이디로 주문서 조회
    public OrderSheet findOrderSheet(Long orderSheetId){
        return orderSheetService.find(orderSheetId);
    }

    // 주문서 객체 > 주문 객체 변환
    public Order toOrder(OrderSheet orderSheet){
        return new Order(
                orderSheet.getId(),
                null,
                orderSheet.getCustomerId(),
                Order.State.NEW,
                orderSheet.getTotalAmount(),
                null
        );
    }
}
