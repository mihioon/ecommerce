package com.hhplus.ecommerce.domain.customer.service;

import com.hhplus.ecommerce.domain.customer.model.Customer;
import com.hhplus.ecommerce.domain.customer.model.CustomerAmountHistory;
import com.hhplus.ecommerce.domain.customer.repository.CustomerServiceRepository;
import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerServiceRepository customerServiceRepository;

    @Override
    @Transactional
    public CustomerDTO saveCustomerAmount(CustomerDTO param) {
        Customer customer = customerServiceRepository.findCustomerByCustomerId(param.getCustomerId());

        // 충전 내역 저장
        CustomerAmountHistory customerAmountHistory = CustomerAmountHistory.builder()
                .customer(customer)
                .type(param.getType())
                .amount(param.getChargeAmount())
                .build();
        customerServiceRepository.saveAmountHistory(customerAmountHistory);

        // 잔액 업데이트
        customer.setAmount(customer.getAmount().add(param.getChargeAmount()));

        CustomerDTO result = CustomerDTO.builder()
                                .customerId(customer.getId())
                                .amount(customer.getAmount())
                                .build();
        return result;
    }
}
