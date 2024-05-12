package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.object.Order;
import com.hhplus.ecommerce.domain.order.object.OrderHistory;
import com.hhplus.ecommerce.domain.order.object.OrderProduct;
import com.hhplus.ecommerce.domain.order.repository.OrderRepository;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(new OrderEntity(
                order.getOrderId(),
                order.getCustomerId(),
                order.getDateTime(),
                order.getOrderState(),
                order.getTotalPrice()
        ));
    }

    public void saveOrderProductList(String orderId, List<OrderProduct> orderProducts){
        OrderEntity orderEntity = orderRepository.findOrder(orderId);
        orderProducts.stream()
                .map(orderProduct -> new OrderProductEntity(
                        orderEntity,
                        orderProduct.getProductId(),
                        orderProduct.getQuantity(),
                        orderProduct.getProdTotalPrice()
                        )

                );

    }

    public OrderHistory findOrder(String orderId){
        OrderEntity orderEntity =  orderRepository.findOrder(orderId);
        List<OrderProductEntity> orderProductEntities = orderEntity.getOrderProducts();
        OrderHistory orderHistory = new OrderHistory(orderEntity.getOrderId(),
                orderEntity.getCustomerId(),
                orderEntity.getOrderDate(),
                orderEntity.getOrderState(),
                orderEntity.getTotalPrice(),
                orderProductEntities.stream()
                        .map(entity -> new OrderProduct(
                                entity.getProductId(),
                                entity.getQuantity(),
                                entity.getProdTotalPrice()
                        )).collect(Collectors.toList())
        );
        return orderHistory;
    }
}
