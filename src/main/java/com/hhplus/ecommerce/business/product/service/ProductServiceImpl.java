package com.hhplus.ecommerce.business.product.service;

import com.hhplus.ecommerce.business.product.model.Product;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;
import com.hhplus.ecommerce.exception.product.ProductsNotFoundException;
import com.hhplus.ecommerce.infrastructure.product.ProductJpaRepository;
import com.hhplus.ecommerce.infrastructure.product.ProductStatisticJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductJpaRepository productJpaRepo;
    private final ProductStatisticJpaRepository productStatJpaRepo;

    @Autowired
    public ProductServiceImpl(ProductJpaRepository productJpaRepo, ProductStatisticJpaRepository productStatJpaRepo) {
        this.productJpaRepo = productJpaRepo;
        this.productStatJpaRepo = productStatJpaRepo;
    }
    @Override
    @Transactional(readOnly = true)
    public ProductDTO searchProduct(Long param) {
        Optional<Product> productOptional = productJpaRepo.findById(param);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("해당 상품은 존재하지 않습니다.");
        } else {
            Product product = productOptional.get();
            ProductDTO result = new ProductDTO(product.getId(),
                    product.getProductNm(),
                    product.getSalePrice(),
                    product.getStockQuantity());
            return result;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProductStatistic() {
        Pageable pageable = PageRequest.of(0, 5);
        List<Product> products = productStatJpaRepo.findTopNProductsByCheckOutCnt(pageable);

        if(products.isEmpty()){
            throw new ProductsNotFoundException("조회된 상품이 없습니다.");
        } else {
            List<ProductDTO> result = products.stream().map(product -> new ProductDTO(product.getId(),
                                                    product.getProductNm(),
                                                    product.getSalePrice(),
                                                    product.getStockQuantity()))
                                        .collect(Collectors.toList());
            return result;
        }
    }
}
