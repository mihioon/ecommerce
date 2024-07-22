package com.hhplus.ecommerce.domain.product;

import java.util.List;

public interface ProductRepository {
    public Product findProductById(Long productId);
    public void minusQuantity(Long productId, Long orderQuantity);
    public void plusQuantity(Long productId, Long orderQuantity);
    public Product.Detail findProductDetailById(Long productId);
    public List<Product> findTop5Id();
}
