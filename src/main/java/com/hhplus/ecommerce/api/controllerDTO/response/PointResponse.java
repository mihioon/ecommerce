package com.hhplus.ecommerce.api.controllerDTO.response;

import com.hhplus.ecommerce.domain.user.PointCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PointResponse {
    private List<PointCommand> pointHistories;
}
