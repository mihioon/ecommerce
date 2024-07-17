package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.exception.product.NotFoundException;
import com.hhplus.ecommerce.infrastructure.order.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderProductReader {
    private final OrderRepository orderRepository;

    public List<OrderProduct> read(String orderId){
        List<OrderProduct> orderProducts = orderRepository.findOrderProducts(orderId);
        if(orderProducts.isEmpty()){
            throw new NotFoundException("해당 주문번호에 해당하는 주문상세내역이 없습니다.");
        }
        return orderProducts;
    }
}
