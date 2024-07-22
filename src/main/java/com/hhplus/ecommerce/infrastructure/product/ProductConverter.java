package com.hhplus.ecommerce.infrastructure.product;

import com.hhplus.ecommerce.domain.product.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public static Product toDomain(ProductEntity productEntity){
        ProductDetailEntity productDetailEntity = productEntity.getProductDetailEntity();
        return new Product(
                productEntity.getId(),
                productEntity.getProductNm(),
                productEntity.getPrice(),
                new Product.Detail(
                        productDetailEntity.getId(),
                        productDetailEntity.getStockQuantity(),
                        productDetailEntity.getLikeCnt(),
                        productDetailEntity.getDayOrderCnt()
                )
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
}
