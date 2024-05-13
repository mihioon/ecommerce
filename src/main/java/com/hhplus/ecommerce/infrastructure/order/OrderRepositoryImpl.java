package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.repository.OrderRepository;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository jpaRepo;

    public OrderRepositoryImpl(OrderJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void save(OrderEntity order) {
        jpaRepo.save(order);
    }

    @Override
    public OrderEntity findOrder(String orderId) {
        return jpaRepo.findById(orderId).orElseThrow(() -> new EntityNotFoundException("주문번호에 해당하는 주문을 찾지 못했습니다. - order: " + orderId));
    }
}
