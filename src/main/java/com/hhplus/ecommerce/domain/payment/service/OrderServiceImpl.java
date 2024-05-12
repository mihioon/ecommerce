package com.hhplus.ecommerce.domain.payment.service;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.payment.model.Order;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.OrderDTO;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.infrastructure.payment.OrderDetailJpaRepository;
import com.hhplus.ecommerce.infrastructure.payment.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService{
    private final OrderJpaRepository orderJpaRepository;
    private final OrderDetailJpaRepository orderDetailJpaRepository;

    @Autowired
    public OrderServiceImpl(OrderJpaRepository orderJpaRepository, OrderDetailJpaRepository orderDetailJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDetailJpaRepository = orderDetailJpaRepository;
    }

    @Override
    public OrderDTO saveOrder(Customer customer, PaymentDTO param) {
        Order order = Order.builder().
                        customer(customer).
                        orderDate(new Date()).
                        orderState(param.getOrderState()).
                        totalPrice(param.getTotalPrice()).
                        build();
        order = orderJpaRepository.save(order);
        return new OrderDTO(order.getId(), order.getOrderState(), order.getTotalPrice(), order.getOrderDate() );
    }

    @Override
    public OrderDTO updateOrder(Customer customer, OrderDTO param) {
        return null;
    }

    @Override
    public OrderDTO saveOrderList(Long orderNumber, ProductDTO[] param) {
        return null;
    }
}
