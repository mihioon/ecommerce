package com.hhplus.ecommerce.application.customer;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.customer.service.CustomerService;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import jakarta.transaction.Transactional;

public class ChargeCustomerAmountUseCase {
    private final CustomerService customerService;

    public ChargeCustomerAmountUseCase(CustomerService customerService){
        this.customerService = customerService;
    }

    @Transactional
    public CustomerDTO chargeCustomerAmount(CustomerDTO customerDTO){
        // 사용자 조회
        Customer customer = customerService.findCustomerByCustomerId(customerDTO.getCustomerId());
        // 충전 내역 저장
        customerDTO.setType("CHARGE");
        customerService.saveAmountHistory(customerDTO, customer);
        // 사용자 금액 업데이트
        customer = customerService.updateChargeCustomer(customer, customerDTO.getAmount());

        return new CustomerDTO(customer.getId(), customer.getAmount());
    }
}
