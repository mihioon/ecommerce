package com.hhplus.ecommerce.domain.product.service;

import com.hhplus.ecommerce.domain.product.model.Product;
import com.hhplus.ecommerce.domain.product.repository.ProductServiceRepository;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.exception.product.ProductsNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductServiceRepository productServiceRepository;

    @Override
    @Transactional(readOnly = true)
    public ProductDTO searchProduct(Long param) {
        Product product = productServiceRepository.findProductInfoByProductId(param);
        ProductDTO result = ProductDTO.builder()
                                .productId(product.getId())
                                .productNm(product.getProductNm())
                                .salePrice(product.getSalePrice())
                                .stockQuantity(product.getStockQuantity())
                                .build();
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProductStatistic() {
        List<Product> products = productServiceRepository.findProductsOrderbyCheckOutCntTop5();

        if(products.isEmpty()){
            throw new ProductsNotFoundException("조회된 상품이 없습니다.");
        }
        List<ProductDTO> result = products.stream().map(product -> ProductDTO.builder()
                        .productId(product.getId())
                        .productNm(product.getProductNm())
                        .salePrice(product.getSalePrice())
                        .build())
                .collect(Collectors.toList());
        return result;
    }
}
