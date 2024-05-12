package com.hhplus.ecommerce.domain.payment.service;

import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.infrastructure.payment.OrderDetailJpaRepository;
import com.hhplus.ecommerce.infrastructure.payment.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final OrderJpaRepository orderJpaRepository;
    private final OrderDetailJpaRepository orderDetailJpaRepository;

    @Autowired
    public PaymentServiceImpl(OrderJpaRepository orderJpaRepository, OrderDetailJpaRepository orderDetailJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDetailJpaRepository = orderDetailJpaRepository;
    }
    @Override
    public List<ProductDTO> cartCheckOut(PaymentDTO param) {

        //throw new

        //결제 승인 처리

        //주문 상태 업데이트

        return null;
    }
}
