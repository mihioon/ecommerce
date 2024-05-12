package com.hhplus.ecommerce.domain.customer.service;

import com.hhplus.ecommerce.domain.customer.object.PointHistory;
import com.hhplus.ecommerce.domain.customer.repository.PointHistoryRepository;
import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointHistoryService {
    //@RequiredArgsConstructor : final 필드 또는 @NonNull 어노테이션이 붙은 필드를 포함한 생성자를 자동으로 생성
    private final PointHistoryRepository pointHistoryRepo;


    public List<PointHistory> findHistoryByCustomerId(Long customerId) {
        List<PointHistoryEntity> entityList =  pointHistoryRepo.findByCustomerId(customerId);
        return entityList.stream()
                .map(entity -> new PointHistory(
                        entity.getCustomerId(),
                        entity.getDateTime(),
                        entity.getType(),
                        entity.getPoint()))
                .collect(Collectors.toList());
    }

    public void savePointHistory(PointHistory param) {
        pointHistoryRepo.savePointHistory(new PointHistoryEntity(
                                                param.getCustomerId(),
                                                param.getDateTime(),
                                                param.getType(),
                                                param.getPoint()));
    }
}
