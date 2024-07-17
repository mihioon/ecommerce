package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderProductReader {
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    public List<OrderProduct> read(Long orderId){
        List<OrderProduct> orderProducts = orderRepository.findOrderProducts(orderId);
        NullChecker.checkNotNull(orderProducts, "orderProducts");
        return orderProducts;
    }
}
