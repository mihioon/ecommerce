package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductStatisticJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT ps.product FROM ProductStatistic ps ORDER BY ps.checkOutCnt DESC")
    public List<ProductEntity> findTopNProductsByCheckOutCnt(Pageable pageable);
}
