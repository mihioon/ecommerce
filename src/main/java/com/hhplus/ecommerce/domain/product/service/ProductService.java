package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;

import java.util.List;

public interface ProductService {
    public ProductDTO searchProduct(Long param);
    public List<ProductDTO> searchProductStatistic();
}
