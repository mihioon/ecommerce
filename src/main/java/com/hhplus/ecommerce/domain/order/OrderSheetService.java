package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSheetService {
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    public Long create(OrderSheet orderSheet) {
        Long orderSheetId = orderRepository.saveOrderSheet(orderSheet);
        NullChecker.checkNotNull(orderSheet, "orderSheet");
        return orderSheetId;
    }

    public OrderSheet find(Long orderSheetId) {
        OrderSheet orderSheet = orderRepository.findOrderSheet(orderSheetId);
        NullChecker.checkNotNull(orderSheet, "orderSheet");
        return orderSheet;
    }
}
