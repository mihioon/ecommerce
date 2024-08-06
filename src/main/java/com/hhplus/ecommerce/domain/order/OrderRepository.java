package com.hhplus.ecommerce.domain.order;

import java.util.List;

public interface OrderRepository {
    public Order findOrder(Long orderId);
    public OrderSheet findOrderSheet(Long orderSheetId);
    public Long persistOrder(Order order);
    public void persistOrderProducts(Long orderId, List<OrderProduct> orderProducts);
    public List<OrderProduct> findOrderProducts(Long orderId);

    public void updateState(Long orderId, Order.State state);

    public Long saveOrderSheet(OrderSheet orderSheet);
}
