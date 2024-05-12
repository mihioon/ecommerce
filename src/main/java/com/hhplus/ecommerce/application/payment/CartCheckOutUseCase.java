package com.hhplus.ecommerce.application.payment;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.customer.service.CustomerService;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.domain.payment.service.OrderService;
import com.hhplus.ecommerce.domain.payment.service.PaymentService;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.OrderDTO;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import jakarta.transaction.Transactional;

public class CartCheckOutUseCase {
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final OrderService orderService;

    public CartCheckOutUseCase(CustomerService customerService, PaymentService paymentService, OrderService orderService){
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.orderService = orderService;
    }

    public PaymentDTO cartCheckOut(PaymentDTO param){
        // 주문 금액 계산 - front?

        // 사용자 조회
        Customer customer = customerService.findCustomerByCustomerId(param.getCustomerId());
        // 주문 내역 저장
        OrderDTO order =  orderService.saveOrder(customer, param);
        // 주문 상세 내역 저장
        orderService.saveOrderList(order.getOrderId(), param.getProductList());

        // 결제
        boolean paymentSuccess = paymentOrder(param);

        // 주문 상태 저장
        if(paymentSuccess){
            order.setOrderState("CNF");
        } else {
            order.setOrderState("PND");
        }
        orderService.updateOrder(customer, order);

        return null;
    }

    @Transactional
    public void paymentOrder(PaymentDTO param){
        try {
            // 사용자 잔액 조회
            Customer customer = customerService.findCustomerByCustomerId(param.getCustomerId());

            // 사용자 잔액 차감
            CustomerDTO customerDTO = new CustomerDTO(param.getCustomerId(), "USE", param.getTotalPrice());
            customerService.saveAmountHistory(customerDTO, customer);

            //return true;
        } catch (Exception e){
            //return false;
        }
}
