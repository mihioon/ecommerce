package com.hhplus.ecommerce.business.customer.service;

import com.hhplus.ecommerce.business.customer.model.Customer;
import com.hhplus.ecommerce.business.customer.model.CustomerAmountHistory;
import com.hhplus.ecommerce.business.customer.service.serviceDTO.CustomerDTO;
import com.hhplus.ecommerce.exception.InputValidationException;
import com.hhplus.ecommerce.infrastructure.customer.CustomerAmountHistoryJpaRepository;
import com.hhplus.ecommerce.infrastructure.customer.CustomerJpaRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerJpaRepository customerJpaRepo;
    private final CustomerAmountHistoryJpaRepository historyJpaRepo;

    public CustomerServiceImpl(CustomerJpaRepository customerJpaRepo, CustomerAmountHistoryJpaRepository historyJpaRepo) {
        this.customerJpaRepo = customerJpaRepo;
        this.historyJpaRepo = historyJpaRepo;
    }

    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        Optional<Customer> customerOptional = customerJpaRepo.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new InputValidationException("해당 사용자는 존재하지 않습니다.");
        }
    }

    @Override
    public List<CustomerAmountHistory> findHistoryByCustomerId(Long customerId) {
        return historyJpaRepo.findByCustomerId(customerId);
    }

    @Override
    public CustomerAmountHistory saveAmountHistory(CustomerDTO param, Customer customer) {
        String type = param.getType();
        BigDecimal amount = param.getAmount();
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InputValidationException("금액은 0보다 작을 수 없습니다.");
        } else {
            CustomerAmountHistory result = historyJpaRepo.save(new CustomerAmountHistory(customer, type, amount));
            return result;
        }
    }

    @Override
    public Customer updateChargeCustomer(Customer customer, BigDecimal chargeAmount) {
        customer.setAmount(customer.getAmount().add(chargeAmount));
        return customerJpaRepo.save(customer);
    }
}
