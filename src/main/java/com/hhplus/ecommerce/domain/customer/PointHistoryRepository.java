package com.hhplus.ecommerce.domain.customer;

import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;

import java.util.List;

public interface PointHistoryRepository {
    List<PointHistoryEntity> findByCustomerId(Long customerId);
    void savePointHistory(PointHistoryEntity pointHistory);
}
