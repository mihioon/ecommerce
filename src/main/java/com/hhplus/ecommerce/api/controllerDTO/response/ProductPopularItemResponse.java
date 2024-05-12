package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;
import lombok.Builder;

import java.util.List;

@Builder
public class ProductPopularItemResponse {
    private String code;
    private String message;
    private List<ProductDTO> body;
}
