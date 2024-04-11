package com.hhplus.ecommerce.api.customer.controllerDTO;

import com.hhplus.ecommerce.domain.customer.service.serviceDTO.CustomerDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerAmountResponse {
    // 클라이언트가 반환 받을 객체
    private String code;
    private String message;
    private CustomerDTO body;

}
