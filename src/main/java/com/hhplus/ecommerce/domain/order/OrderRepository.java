package com.hhplus.ecommerce.domain.order;

import com.hhplus.ecommerce.infrastructure.order.OrderProductEntity;

import java.util.List;

public interface OrderRepository {
    public Order findOrder(String orderId);
    public OrderSheet findOrderSheet(Long orderSheetId);
    public void persistOrder(Order order);
    public void persistOrderProducts(List<OrderProduct> orderProducts);

    public List<OrderProduct> findOrderProducts(String orderId);
}
