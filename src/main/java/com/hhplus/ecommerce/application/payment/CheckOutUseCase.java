package com.hhplus.ecommerce.application.payment;

import com.hhplus.ecommerce.domain.customer.CustomerService;
import com.hhplus.ecommerce.domain.customer.PointHistoryService;
import com.hhplus.ecommerce.domain.order.OrderHistory;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderService;
import com.hhplus.ecommerce.domain.product.StockService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        return null;
    }
}
