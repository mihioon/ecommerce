package com.hhplus.ecommerce.domain.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;

public interface CustomerRepository {
    CustomerEntity findById(Long id);

    CustomerEntity save(CustomerEntity customerEntity);
}