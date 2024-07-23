package com.hhplus.ecommerce.domain.product;

import java.util.List;

public interface ProductRepository {
    public Product findProductById(Long productId);
    public Long saveProduct(Product product);
    public void updateQuantity(Long productId, Long Quantity);
    public Long findStockById(Long productId);
    public Product.Detail findProductDetailById(Long productId);
    public List<Product> findPopularProduct(int pageSize);
}
