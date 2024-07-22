package com.hhplus.ecommerce.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    @Query("SELECT p FROM ProductEntity p JOIN p.productDetailEntity d ORDER BY d.likeCnt DESC, d.dayOrderCnt DESC")
    List<ProductEntity> findTop5ProductsByOrderByLikeCntDescDayOrderCntDesc();
}
