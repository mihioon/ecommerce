package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductManager {
    private final ProductRepository productRepository;

    public Long save(Product product) {
        NullChecker.checkNotNull(product, "product");
        return productRepository.saveProduct(product);
    }
}
