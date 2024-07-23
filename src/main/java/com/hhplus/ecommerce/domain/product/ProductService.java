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
    private final ProductManager productManager;
    private final NullChecker nullChecker;

    @Transactional(readOnly = true)
    public Product findProduct(Long productId) {
        return productReader.read(productId);
    }

    @Transactional(readOnly = true)
    public List<Product> findPopularProduct(int pageSize) {
        return productReader.findPopularProduct(pageSize);
    }

    public Long saveProduct(Product product) {
        return productManager.save(product);
    }
}
