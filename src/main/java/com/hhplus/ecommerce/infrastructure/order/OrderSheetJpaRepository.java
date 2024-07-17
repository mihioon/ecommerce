package com.hhplus.ecommerce.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSheetJpaRepository extends JpaRepository<OrderSheetEntity, Long> {
}
