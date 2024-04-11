package com.hhplus.ecommerce.domain.product.repository;

import com.hhplus.ecommerce.domain.product.model.Product;

import java.util.List;

public interface ProductServiceRepository {
    public Product findProductInfoByProductId(Long param);
    public List<Product> findProductsOrderbyCheckOutCntTop5();
}
