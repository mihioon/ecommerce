package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.business.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {
}
