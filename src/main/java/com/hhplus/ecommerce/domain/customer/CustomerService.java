package com.hhplus.ecommerce.domain.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepo;

    public Customer findCustomer(Long customerId) {
        CustomerEntity customerEntity = customerRepo.findById(customerId);
        return new Customer(customerEntity.getId(), customerEntity.getPoint());
    }

    public void updateChargeCustomer(Customer customer) {
        CustomerEntity customerEntity = customerRepo.findById(customer.getId());
        customerEntity.updatePoint(customer.getPoint());
    }

    public Customer save(Customer customer) {
        CustomerEntity customerEntity = customerRepo.save(new CustomerEntity(customer.getPoint()));
        return new Customer(customerEntity.getId(), customerEntity.getPoint());
    }
}
