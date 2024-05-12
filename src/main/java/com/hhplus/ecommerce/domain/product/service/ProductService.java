package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.product.object.Product;
import com.hhplus.ecommerce.exception.product.ProductsNotFoundException;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.ProductStatisticJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }

    @Transactional(readOnly = true)
    public List<Product> searchProductStatistic() {
        Pageable pageable = PageRequest.of(0, 5);
        List<ProductEntity> products = productStatJpaRepo.findTopNProductsByCheckOutCnt(pageable);

        if(products.isEmpty()){
            throw new ProductsNotFoundException("조회된 상품이 없습니다.");
        } else {
            List<Product> result = products.stream().map(product -> new Product(product.getId(),
                                                    product.getProductNm(),
                                                    product.getSalePrice(),
                                                    product.getStockQuantity()))
                                        .collect(Collectors.toList());
            return result;
        }
    }
}
