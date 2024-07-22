package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.domain.product.Product;
import com.hhplus.ecommerce.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final ProductJpaRepository productJpaRepo;
    private final ProductDetailJpaRepository productDetailJpaRepo;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepo, ProductDetailJpaRepository productDetailJpaRepo) {
        this.productJpaRepo = productJpaRepo;
        this.productDetailJpaRepo = productDetailJpaRepo;
    }


    public Product findProductById(Long productId) {
        Optional<ProductEntity> productEntity = productJpaRepo.findById(productId);
        return productEntity.map(ProductConverter::toDomain).orElse(null);
    }

    // 주문 재고 감소
    public void minusQuantity(Long productId, Long orderQuantity){
        ProductDetailEntity productDetailEntity = productDetailJpaRepo.findById(productId).orElseThrow();
        productDetailEntity.minusQuantity(orderQuantity);
    }

    // 주문 재고 증가
    public void plusQuantity(Long productId, Long orderQuantity){
        ProductDetailEntity productDetailEntity = productDetailJpaRepo.findById(productId).orElseThrow();
        productDetailEntity.plusQuantity(orderQuantity);
    }

    // 상품 상세 조회 By Id
    public Product.Detail findProductDetailById(Long productId){
        Optional<ProductDetailEntity> productDetailEntity = productDetailJpaRepo.findById(productId);
        return productDetailEntity.map(ProductConverter::toDomain).orElse(null);
    }

    // 인기상품 5개 조회
    public List<Product> findTop5Id() {
        List<ProductEntity> productEntities =  productJpaRepo.findTop5ProductsByOrderByLikeCntDescDayOrderCntDesc();

        return productEntities.stream()
                .map(ProductConverter::toDomain)
                .collect(Collectors.toList());
    }
}
