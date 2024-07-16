package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.domain.customer.CustomerRepository;
import com.hhplus.ecommerce.infrastructure.customer.entity.CustomerEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository jpaRepo;

    public CustomerRepositoryImpl(CustomerJpaRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    @Override
    public CustomerEntity findById(Long id) {
        return jpaRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("사용자를 찾지 못했습니다. - id: " + id));
    }

    @Override
    public CustomerEntity save(CustomerEntity customerEntity) {
        return jpaRepo.save(customerEntity);
    }
}