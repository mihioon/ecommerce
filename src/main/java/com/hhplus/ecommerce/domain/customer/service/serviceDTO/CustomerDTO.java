package com.hhplus.ecommerce.domain.customer.service.serviceDTO;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerDTO {
    // 서비스 - 컨트롤러 간 데이터 전송을 위한 객체
    private long customerId;
    private String type;
    private BigDecimal chargeAmount;
    private BigDecimal amount;
}
