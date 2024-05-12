package com.hhplus.ecommerce.domain.customer.service;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.customer.repository.CustomerRepository;
import com.hhplus.ecommerce.infrastructure.customer.CustomerJpaRepository;
import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;

    public Customer findCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepo.findById(customerId);
        return new Customer(customerEntity.getId(), customerEntity.getPoint());
    }

    public Customer updateChargeCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepo.findById(customer.getId());
        customerEntity.updatePoint(customer.getPoint());
        return customer;
    }
}
