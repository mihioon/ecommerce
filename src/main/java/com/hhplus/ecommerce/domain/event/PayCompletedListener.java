package com.hhplus.ecommerce.domain.event;

import com.hhplus.ecommerce.api.controllerDTO.request.CheckOutRequest;
import com.hhplus.ecommerce.domain.order.Order;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderService;
import com.hhplus.ecommerce.domain.order.OrderSheet;
import com.hhplus.ecommerce.domain.product.StockCommand;
import com.hhplus.ecommerce.domain.product.StockService;
import com.hhplus.ecommerce.domain.user.PointCommand;
import com.hhplus.ecommerce.domain.user.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayCompletedListener {
    OrderService orderService;
    StockService stockService;
    PointService pointService;

    @Async
    @EventListener
    public void handleEvent(PayCompleteEvent event) {
        log.info("이벤트 수신: " + event.getMessage());

        CheckOutRequest checkOutRequest = event.getCheckOutRequest();
        Long orderId = checkOutRequest.getOrderId();

        // 주문완료 상태 업데이트
        orderService.updateState(orderId, Order.State.PAID);

        // 주문 히스토리 저장
        orderService.saveHistory(orderId);
    }

    @Async
    @EventListener
    public void handleEvent(PayFailedEvent event) {
        log.info("이벤트 수신: " + event.getMessage());

        CheckOutRequest checkOutRequest = event.getCheckOutRequest();
        Long orderId = checkOutRequest.getOrderId();

        // 포인트 롤백
        PointCommand pointCommand = checkOutRequest.toPointCommand();
        pointService.chargePoint(pointCommand);

        // 재고 롤백
        OrderSheet orderSheet = orderService.findOrderSheet(checkOutRequest.getOrderSheetId());
        List<StockCommand> stockList = orderSheet.toStockList();
        stockService.addStocks(stockList);

        // 주문 삭제
        orderService.updateState(orderId, Order.State.FAILED);
    }
}
