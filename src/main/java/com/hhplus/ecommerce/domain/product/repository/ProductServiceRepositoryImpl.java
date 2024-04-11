package com.hhplus.ecommerce.domain.product.repository;

import com.hhplus.ecommerce.domain.customer.model.Customer;
import com.hhplus.ecommerce.domain.product.model.Product;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.ProductStatisticJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductServiceRepositoryImpl implements ProductServiceRepository{
    private final ProductJpaRepository productJpaRepo;
    private final ProductStatisticJpaRepository productStatJpaRepo;

    @Autowired
    public ProductServiceRepositoryImpl(ProductJpaRepository productJpaRepo, ProductStatisticJpaRepository productStatJpaRepo) {
        this.productJpaRepo = productJpaRepo;
        this.productStatJpaRepo = productStatJpaRepo;
    }

    @Override
    public Product findProductInfoByProductId(Long param) {
        Optional<Product> productOptional = productJpaRepo.findById(param);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new RuntimeException("해당 상품은 존재하지 않습니다.");
        }
    }

    @Override
    public List<Product> findProductsOrderbyCheckOutCntTop5() {
        Pageable pageable = PageRequest.of(0, 5);
        return productStatJpaRepo.findTopNProductsByCheckOutCnt(pageable);
    }
}
