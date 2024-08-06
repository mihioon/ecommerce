package com.hhplus.ecommerce.application;

import com.hhplus.ecommerce.api.controllerDTO.request.OrderRequest;
import com.hhplus.ecommerce.domain.order.*;
import com.hhplus.ecommerce.domain.payment.Payment;
import com.hhplus.ecommerce.domain.payment.PaymentService;
import com.hhplus.ecommerce.domain.user.PointCommand;
import com.hhplus.ecommerce.domain.user.UserService;
import com.hhplus.ecommerce.domain.user.PointService;
import com.hhplus.ecommerce.domain.product.StockCommand;
import com.hhplus.ecommerce.domain.product.StockService;
import com.hhplus.ecommerce.infrastructure.redis.RedisLockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckOutProvider {
    private final UserService userService;
    private final PointService pointHistoryService;
    private final OrderService orderService;
    private final StockService stockService;
    private final RedisLockRepository redisLockRepository;
    private final PointService pointService;
    private final OrderSheetService orderSheetService;
    private final PaymentService paymentService;

    public void checkOut(OrderRequest orderRequest) {
        Long orderSheetId = orderRequest.getOrderSheetId();

        // 포인트 차감
        PointCommand pointCommand = orderRequest.toPointCommand();
        pointService.usePoint(pointCommand);

        // 재고 차감
        OrderSheet orderSheet = orderSheetService.find(orderSheetId);
        List<StockCommand> stocks = toDomain(orderSheet.getOrderProducts());
        stockService.deductStocks(stocks);

        // 주문 생성
        orderService.createOrder(orderSheetId); // 주문생성

        // 결제
        Payment payment = orderRequest.toPayment();
        paymentService.pay(payment);

        // 주문완료 상태 업데이트
        orderService.updateState(Order.State.PAID);
    }

    public List<StockCommand> toDomain(List<OrderProduct> orderProductList){
        return orderProductList.stream()
                .map(orderProduct -> new StockCommand(
                        orderProduct.getProductId(),
                        orderProduct.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}
