package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PointHistoryJpaRepository extends JpaRepository<PointHistoryEntity, Long> {
    List<PointHistoryEntity> findByCustomerIdOrderByDateTimeDesc(Long customerId);
}
