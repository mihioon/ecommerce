package com.hhplus.ecommerce.application.payment;

import com.hhplus.ecommerce.domain.customer.Customer;
import com.hhplus.ecommerce.domain.customer.PointHistory;
import com.hhplus.ecommerce.domain.customer.CustomerService;
import com.hhplus.ecommerce.domain.customer.PointHistoryService;
import com.hhplus.ecommerce.domain.order.Order;
import com.hhplus.ecommerce.domain.order.OrderHistory;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderService;
import com.hhplus.ecommerce.domain.product.ProductQuantity;
import com.hhplus.ecommerce.domain.product.StockService;
import com.hhplus.ecommerce.util.OrderState;
import com.hhplus.ecommerce.util.PointType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CheckOutUseCase {
    private final CustomerService customerService;
    private final PointHistoryService pointHistoryService;
    private final OrderService orderService;
    private final StockService stockService;

    @Transactional
    public OrderHistory checkOut(Long customerId, BigDecimal totalPrice, List<OrderProduct> orderProducts){

        // ------------------------------------------- 결제
        LocalDateTime dateTime = LocalDateTime.now();

        // 사용자 조회
        Customer customer = customerService.findCustomer(customerId);

        // 포인트 내역 저장
        PointHistory pointHistory = new PointHistory(customerId, dateTime, PointType.USE, totalPrice);
        pointHistoryService.savePointHistory(pointHistory);

        // 포인트 결제
        customer.usePoint(totalPrice);

        // ------------------------------------------- 결제
        // ------------------------------------------- 주문서생성 결제전으로 처리
        // 주문서 생성
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        String orderId = dateFormat.format(dateTime)+customerId;

        Order order = new Order(orderId, customerId, dateTime, OrderState.NEW, totalPrice);
        orderService.createOrder(order, orderProducts);

        // ------------------------------------------- 주문서생성 결제전으로 처리
        // 재고차감
        List<ProductQuantity> productQuantities = orderProducts.stream()
                        .map(orderProduct -> new ProductQuantity(
                                orderProduct.getProductId(),
                                orderProduct.getQuantity())
                        ).toList();
        stockService.deductStock(productQuantities);

        // 주문 내역 조회
        return orderService.findOrder(orderId);
    }
}
