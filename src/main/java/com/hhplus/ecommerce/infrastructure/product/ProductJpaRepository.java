package com.hhplus.ecommerce.infrastructure.product;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.productDetailEntity d ORDER BY d.likeCnt DESC, d.dayOrderCnt DESC")
    List<ProductEntity> findTopProductsByOrderByLikeCntDescDayOrderCntDesc(Pageable pageable);

    @Query("SELECT p FROM ProductEntity p JOIN FETCH p.productDetailEntity WHERE p.id = :id")
    ProductEntity findByIdWithDetail(@Param("id") Long id);
}
