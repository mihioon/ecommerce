package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.domain.product.Product;
import com.hhplus.ecommerce.domain.product.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    // 상품 조회
    public Product findProductById(Long productId) {
        Optional<ProductEntity> productEntity = productJpaRepo.findById(productId);
        return productEntity.map(ProductConverter::toDomain).orElse(null);
    }

    @Override
    public Long saveProduct(Product product) {
        ProductEntity productEntity = ProductConverter.toEntity(product);
        return productJpaRepo.save(productEntity).getId();
    }

    // 상품 상세 조회 By Id
    public Product.Detail findProductDetailById(Long productId){
        Optional<ProductDetailEntity> productDetailEntity = productDetailJpaRepo.findById(productId);
        return productDetailEntity.map(ProductConverter::toDomain).orElse(null);
    }

    @Override
    public void updateQuantity(Long productId, Long quantity) {
        ProductDetailEntity entity= productDetailJpaRepo.findById(productId).orElseThrow();
        entity.setQuantity(quantity);
    }

    @Override
    public Long findStockById(Long productId) {
        Optional<ProductDetailEntity> productDetailEntity = productDetailJpaRepo.findById(productId);
        return productDetailEntity.map(ProductConverter::toStock).orElse(null);
    }

    // 인기상품 5개 조회
    public List<Product> findPopularProduct(int pageSize) {
        Pageable pageable = PageRequest.of(0, pageSize);
        List<ProductEntity> productEntities =  productJpaRepo.findTopProductsByOrderByLikeCntDescDayOrderCntDesc(pageable);

        return productEntities.stream()
                .map(ProductConverter::toDomain)
                .collect(Collectors.toList());
    }
}
