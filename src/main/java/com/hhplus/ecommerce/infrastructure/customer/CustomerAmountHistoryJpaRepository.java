package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.domain.customer.model.CustomerAmountHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerAmountHistoryJpaRepository extends JpaRepository<CustomerAmountHistory, Long> {
    List<CustomerAmountHistory> findByCustomerId(Long customerId);
    //CustomerAmountHistory save(CustomerAmountHistory customerAmountHistory);
}
