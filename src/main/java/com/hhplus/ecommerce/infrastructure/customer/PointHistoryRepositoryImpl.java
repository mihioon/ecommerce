package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.domain.customer.PointHistoryRepository;
import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointHistoryRepositoryImpl implements PointHistoryRepository {

    private final PointHistoryJpaRepository jpaRepo;

    public PointHistoryRepositoryImpl(PointHistoryJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public List<PointHistoryEntity> findByCustomerId(Long customerId) {
        return jpaRepo.findByCustomerIdOrderByDateTimeDesc(customerId);
    }

    @Override
    public void savePointHistory(PointHistoryEntity pointHistory) {
        jpaRepo.save(pointHistory);
    }
}
