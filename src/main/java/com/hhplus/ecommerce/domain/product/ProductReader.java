package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductReader {
    private final ProductRepository productRepository;

    // 상품상세정보 없이 상품만 조회
    public Product read(Long productId){
        Product product = productRepository.findProductById(productId);
        NullChecker.checkNotNull(product, "product");
        return product;
    }

    // 인기있는 상위 5개 상품 조회
    public List<Product> findTop5Id(){
        List<Product> products = productRepository.findTop5Id();
        NullChecker.checkNotNull(products, "products");
        return products;
    }
}
