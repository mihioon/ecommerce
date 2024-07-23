package com.hhplus.ecommerce.domain.product;

import com.hhplus.ecommerce.domain.common.OrderState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    ProductService productService;
    // 상품 파사드 통합 테스트
    @Test
    @DisplayName("상품 저장 및 조회 테스트")
    public void test_product_create(){
        // given
        Product.Detail productDetail = new Product.Detail(
                null,
                2L,
                3L,
                4L
        );
        Product product = new Product(
                null,
                "강아지 인형",
                new BigDecimal("100"),
                productDetail
        );

        // when
        Long productId = productService.saveProduct(product);
        Product result = productService.findProduct(productId);

        // then
        assertThat(result.getId()).isEqualTo(productId);
        assertThat(result.getProductNm()).isEqualTo("강아지 인형");
        assertThat(result.getSalePrice()).isEqualByComparingTo(new BigDecimal("100"));
        assertThat(result.getProductDetail().getQuantity()).isEqualTo(2L);
        assertThat(result.getProductDetail().getLikeCnt()).isEqualTo(3L);
        assertThat(result.getProductDetail().getDayOrderCnt()).isEqualTo(4L);
    }
}