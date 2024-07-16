package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.exception.product.NotFoundException;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.ProductStatisticJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductStatisticEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductJpaRepository productJpaRepo;
    private final ProductStatisticJpaRepository productStatJpaRepo;

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        ProductEntity productEntity = productJpaRepo.findById(productId).orElseThrow(() -> new EntityNotFoundException("해당 상품을 찾지 못했습니다. - id: " + productId));
        return productEntity.toDomain();
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductStatistic() {
        Pageable pageable = PageRequest.of(0, 5);
        List<ProductStatisticEntity> productsStatistics = productStatJpaRepo.findTopNProductsByCheckOutCnt(pageable);
        if(productsStatistics.isEmpty()){
            throw new NotFoundException("조회된 상품이 없습니다.");
        } else {
            List<Product> result = productsStatistics.stream()
                    .map(
                            productsStatistic -> (findProduct(productsStatistic.getProductId()))
                    ).collect(Collectors.toList());
            return result;
        }
    }
}
