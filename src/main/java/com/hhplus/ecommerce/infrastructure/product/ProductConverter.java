package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.domain.product.Product;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductConverter {
    public static Product toDomain(ProductEntity productEntity){
        ProductDetailEntity productDetailEntity = productEntity.getProductDetailEntity();
        return new Product(
                productEntity.getId(),
                productEntity.getProductNm(),
                productEntity.getPrice(),
                Optional.ofNullable(productDetailEntity)
                        .map(ProductConverter::toDomain)
                        .orElse(null)
        );
    }

    public static Product.Detail toDomain(ProductDetailEntity productDetailEntity){
        return new Product.Detail(
                productDetailEntity.getId(),
                productDetailEntity.getStockQuantity(),
                productDetailEntity.getLikeCnt(),
                productDetailEntity.getDayOrderCnt()
        );
    }

    public static Long toStock(ProductDetailEntity productDetailEntity){
        return productDetailEntity.getStockQuantity();
    }

    public static ProductEntity toEntity(Product product){
        return new ProductEntity(
                product.getId(),
                product.getProductNm(),
                product.getSalePrice(),
                Optional.ofNullable(product.getProductDetail())
                        .map(ProductConverter::toEntity)
                        .orElse(null)
        );
    }

    public static ProductDetailEntity toEntity(Product.Detail productDetail){
        return new ProductDetailEntity(
                productDetail.getDetailId(),
                productDetail.getQuantity(),
                productDetail.getLikeCnt(),
                productDetail.getDayOrderCnt()
        );
    }
}
