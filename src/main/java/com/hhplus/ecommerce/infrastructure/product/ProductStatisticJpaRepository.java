package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductStatisticEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductStatisticJpaRepository extends JpaRepository<ProductStatisticEntity, Long> {
    @Query("SELECT ps FROM ProductStatisticEntity ps ORDER BY ps.checkOutCnt DESC")
    public List<ProductStatisticEntity> findTopNProductsByCheckOutCnt(Pageable pageable);
}
