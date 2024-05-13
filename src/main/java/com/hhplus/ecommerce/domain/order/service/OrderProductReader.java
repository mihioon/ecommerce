package com.hhplus.ecommerce.domain.order.service;

import com.hhplus.ecommerce.domain.order.repository.OrderProductRepository;
import com.hhplus.ecommerce.exception.product.NotFoundException;
import com.hhplus.ecommerce.infrastructure.order.entity.OrderProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderProductReader {
    private final OrderProductRepository orderProductRepository;

    public List<OrderProductEntity> read(String orderId){
        List<OrderProductEntity> orderProductEntities = orderProductRepository.findOrderProducts(orderId);
        if(orderProductEntities.isEmpty()){
            throw new NotFoundException("해당 주문번호에 해당하는 주문상세내역이 없습니다.");
        }
        return orderProductEntities;
    }
}
