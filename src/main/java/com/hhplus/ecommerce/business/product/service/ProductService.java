package com.hhplus.ecommerce.business.product.service;

import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;

import java.util.List;

public interface ProductService {
    public ProductDTO searchProduct(Long param);
    public List<ProductDTO> searchProductStatistic();
}
