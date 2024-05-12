package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {
}
