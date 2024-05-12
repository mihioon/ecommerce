package com.hhplus.ecommerce.domain.customer.repository;

import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository {
    CustomerEntity findById(Long id);
}