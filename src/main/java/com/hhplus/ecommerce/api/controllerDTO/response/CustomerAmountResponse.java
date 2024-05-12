package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.customer.object.PointHistory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerAmountResponse {
    private List<PointHistory> pointHistories;
}
