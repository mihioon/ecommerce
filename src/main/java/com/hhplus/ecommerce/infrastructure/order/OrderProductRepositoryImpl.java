package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.repository.OrderProductRepository;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderProductRepositoryImpl implements OrderProductRepository {

    private final OrderDetailJpaRepository jpaRepo;

    public OrderProductRepositoryImpl(OrderDetailJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public void saveAll(List<OrderProductEntity> orderProducts) {

    }

    @Override
    public List<OrderProductEntity> findOrderProducts(String orderId) {
        return null;
    }
}
