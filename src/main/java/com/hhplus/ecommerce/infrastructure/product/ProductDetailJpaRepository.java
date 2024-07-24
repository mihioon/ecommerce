package com.hhplus.ecommerce.infrastructure.product;

import jakarta.persistence.LockModeType;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductDetailJpaRepository extends JpaRepository<ProductDetailEntity, Long> {
    @NonNull
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select p from ProductDetailEntity p WHERE p.id = :id")
    Optional<ProductDetailEntity> findByIdWithLock(@Param("id") Long id);
}
