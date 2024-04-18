package com.hhplus.ecommerce.business.payment.service;

import com.hhplus.ecommerce.business.customer.model.Customer;
import com.hhplus.ecommerce.business.payment.model.Order;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.OrderDTO;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.infrastructure.payment.OrderDetailJpaRepository;
import com.hhplus.ecommerce.infrastructure.payment.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
