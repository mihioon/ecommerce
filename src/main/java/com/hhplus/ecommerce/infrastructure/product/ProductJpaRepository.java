package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    public ProductEntity findProductById(Long param);
}
