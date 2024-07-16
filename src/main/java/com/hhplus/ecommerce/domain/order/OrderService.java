package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderReader orderReader;
    private final OrderProductReader orderProductReader;
    private final OrderHistoryBuilder orderHistoryBuilder;
    private final OrderAppender orderAppender;

    public OrderHistory findOrder(String orderId){
        OrderEntity orderEntity = orderReader.read(orderId);
        List<OrderProductEntity> orderProductEntities = orderProductReader.read(orderId);

        OrderHistory orderHistory = orderHistoryBuilder.build(orderEntity, orderProductEntities);

        return orderHistory;
    }

    public void createOrder(Order order, List<OrderProduct> orderProducts) {
        orderAppender.append(order, orderProducts);
    }
}
