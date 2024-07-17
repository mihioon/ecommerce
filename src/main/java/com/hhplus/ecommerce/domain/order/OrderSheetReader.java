package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSheetReader {
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    public OrderSheet read(Long orderSheetId) {
        OrderSheet orderSheet = orderRepository.findOrderSheet(orderSheetId);
        NullChecker.checkNotNull(orderSheet, "orderSheet");
        return orderSheet;
    }
}
