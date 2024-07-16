package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderAppender {
    public final OrderRepository orderRepository;
    public final OrderProductRepository orderProductRepository;

    public void append(Order order, List<OrderProduct> orderProducts){
        orderCreate(order);
        orderProductsCreate(order.getOrderId(), orderProducts);
    }

    void orderCreate(Order order){
        orderRepository.save(new OrderEntity(
                order.getOrderId(),
                order.getCustomerId(),
                order.getDateTime(),
                order.getOrderState(),
                order.getTotalPrice()
        ));
    }

    void orderProductsCreate(String orderId, List<OrderProduct> orderProducts){
        orderProductRepository.saveAll(orderProducts.stream()
                .map(orderProduct -> new OrderProductEntity(
                                orderId,
                                orderProduct.getProductId(),
                                orderProduct.getQuantity(),
                                orderProduct.getProdTotalPrice()
                        )

                ).toList()
        );
    }
}
