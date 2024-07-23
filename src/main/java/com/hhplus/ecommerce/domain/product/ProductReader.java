package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductReader {
    private final ProductRepository productRepository;

    // 상품 상세정보 없이 상품만 조회
    public Product read(Long productId){
        Product product = productRepository.findProductById(productId);
        NullChecker.checkNotNull(product, "Product");
        return product;
    }

    // 상품 상세정보 조회
    public Product.Detail readDetail(Long productId){
        Product.Detail productDetail = productRepository.findProductDetailById(productId);
        NullChecker.checkNotNull(productDetail, "productDetail");
        return productDetail;
    }

    // 인기있는 상위 5개 상품 조회
    public List<Product> findPopularProduct(int pageSize){
        List<Product> products = productRepository.findPopularProduct(5);
        NullChecker.checkNotNull(products, "products");
        return products;
    }
}
