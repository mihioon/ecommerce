package com.hhplus.ecommerce.infrastructure.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; /* Key */

    @Column(nullable = false)
    private String productNm; /* 제품명 */

    @Column(nullable = false)
    private BigDecimal price; /* 제품금액 */

    // 제품 설명...etc.

    // 제품 상세 연관관계
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_detail_id")  // 외래 키 칼럼 이름
    private ProductDetailEntity productDetailEntity;
}
