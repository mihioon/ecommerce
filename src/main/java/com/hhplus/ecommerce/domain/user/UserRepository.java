package com.hhplus.ecommerce.domain.user;

import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    public Optional<User> findById(Long id);

    public BigDecimal findPoint(Long customerId);

    public User save(User user);

    public void usePointAndSaveHistory(PointCommand pointCommand);

    public void chargePointAndSaveHistory(PointCommand pointCommand);

    public List<PointHistoryEntity> findByUserId(Long customerId);
}