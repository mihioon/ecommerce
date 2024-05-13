package com.hhplus.ecommerce.application.payment;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.customer.object.PointHistory;
import com.hhplus.ecommerce.domain.customer.service.CustomerService;
import com.hhplus.ecommerce.domain.customer.service.PointHistoryService;
import com.hhplus.ecommerce.domain.order.object.Order;
import com.hhplus.ecommerce.domain.order.object.OrderHistory;
import com.hhplus.ecommerce.domain.order.object.OrderProduct;
import com.hhplus.ecommerce.domain.order.service.OrderService;
import com.hhplus.ecommerce.util.OrderState;
import com.hhplus.ecommerce.util.PointType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class CheckOutUseCase {
    private final CustomerService customerService;
    private final PointHistoryService pointHistoryService;
    private final OrderService orderService;

    @Transactional
    public OrderHistory checkOut(Long customerId, BigDecimal totalPrice, List<OrderProduct> orderProducts){

        LocalDateTime dateTime = LocalDateTime.now();

        // 사용자 조회
        Customer customer = customerService.findCustomer(customerId);

        // 포인트 내역 저장
        PointHistory pointHistory = new PointHistory(customerId, dateTime, PointType.USE, totalPrice);
        pointHistoryService.savePointHistory(pointHistory);

        // 포인트 결제
        customer.usePoint(totalPrice);

        // 주문서 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String orderId = dateFormat.format(dateTime)+customerId;

        Order order = new Order(orderId, customerId, dateTime, OrderState.NEW, totalPrice);
        orderService.createOrder(order, orderProducts);

        // 주문 내역 조회
        return orderService.findOrder(orderId);
    }
}
