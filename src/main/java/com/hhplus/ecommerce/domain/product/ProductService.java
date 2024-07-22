package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.NullChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService{
    private final ProductReader productReader;
    private final NullChecker nullChecker;

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        return productReader.read(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findPopularProduct(Long productId) {
        return productReader.findTop5Id();
    }
}
