package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<UserEntity, Long> {
}
