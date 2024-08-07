package com.hhplus.ecommerce.application;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import com.hhplus.ecommerce.domain.event.OrderCreatedPublisher;
import com.hhplus.ecommerce.domain.order.*;
import com.hhplus.ecommerce.domain.user.PointCommand;
import com.hhplus.ecommerce.domain.user.PointService;
import com.hhplus.ecommerce.domain.product.StockCommand;
import com.hhplus.ecommerce.domain.product.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CheckOutProvider {
    private final OrderService orderService;
    private final StockService stockService;
    private final PointService pointService;
    private final OrderSheetService orderSheetService;
    private final OrderCreatedPublisher publisher;

    public void checkOut(CheckOutRequest checkOutRequest) {
        Long orderSheetId = checkOutRequest.getOrderSheetId();
        OrderSheet orderSheet = orderSheetService.find(orderSheetId);

        // 포인트 차감
        PointCommand pointCommand = checkOutRequest.toPointCommand();
        pointService.usePoint(pointCommand);

        // 재고 차감
        List<StockCommand> stockList = orderSheet.toStockList();
        stockService.deductStocks(stockList);

        // 주문 생성
        Order order = orderSheet.toOrder();
        Long orderId = orderService.createOrder(order); // 주문생성

        // 주문 생성 완료 이벤트 발행
        publisher.publish(checkOutRequest);
    }
}
