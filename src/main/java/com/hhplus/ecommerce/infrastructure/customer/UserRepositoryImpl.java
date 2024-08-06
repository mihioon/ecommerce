package com.hhplus.ecommerce.infrastructure.customer;

import com.hhplus.ecommerce.domain.user.PointCommand;
import com.hhplus.ecommerce.domain.user.User;
import com.hhplus.ecommerce.domain.user.UserRepository;
import com.hhplus.ecommerce.infrastructure.customer.entity.UserEntity;
import com.hhplus.ecommerce.infrastructure.customer.entity.PointHistoryEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final CustomerJpaRepository userJpaRepo;
    private final PointHistoryJpaRepository pointJpaRepo;

    public UserRepositoryImpl(CustomerJpaRepository userJpaRepo, PointHistoryJpaRepository pointJpaRepo) {
        this.userJpaRepo = userJpaRepo;
        this.pointJpaRepo = pointJpaRepo;
    }

    /**
     * User
     */
    // id로 User 조회
    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> userEntity= userJpaRepo.findById(id);
        return userEntity.map(this::toDomain);
    }

    // id로 포인트 조회
    @Override
    public BigDecimal findPoint(Long customerId) {
        Optional<UserEntity> customerEntity = userJpaRepo.findById(customerId);
        return customerEntity.map(UserEntity::toPoint) // CustomerEntity -> CustomerDomain
                .orElse(BigDecimal.ZERO);
    }

    // 사용자 저장
    @Override
    public User save(User user) {
        UserEntity userEntity  = userJpaRepo.save(toEntity(user));
        return toDomain(userEntity);
    }

    /**
     * Point
     */
    // 포인트 사용 및 내역 저장
    @Override
    @Transactional
    public void usePointAndSaveHistory(PointCommand pointCommand) {
        // 엔티티 조회 및 포인트 추가
        UserEntity userEntity= userJpaRepo.findById(pointCommand.getUserId())
                .orElseThrow(() -> new NoSuchElementException("UserEntity not found"));
        userEntity.minusPoint(pointCommand.getPoint());
        // 포인트 내역 저장
        saveHistory(pointCommand, PointHistoryEntity.Type.CHARGE);
    }

    // 포인트 충전 및 내역 저장
    @Override
    @Transactional
    public void chargePointAndSaveHistory(PointCommand pointCommand) {
        // 엔티티 조회 및 포인트 차감
        UserEntity userEntity= userJpaRepo.findById(pointCommand.getUserId())
                .orElseThrow(() -> new NoSuchElementException("UserEntity not found"));
        userEntity.plusPoint(pointCommand.getPoint());
        // 포인트 내역 저장
        saveHistory(pointCommand, PointHistoryEntity.Type.USE);
    }

    // 포인트 내역 조회
    @Override
    public List<PointHistoryEntity> findByUserId(Long customerId) {
        return pointJpaRepo.findByCustomerIdOrderByDateTimeDesc(customerId);
    }

    /**
     * 공통로직
     */
    private void saveHistory(PointCommand pointCommand, PointHistoryEntity.Type type){
        pointJpaRepo.save(toHistoryEntity(pointCommand, type));
    }

    /**
     * Converter
     */
    private User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getPoint()
        );
    }

    private UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getPoint()
        );
    }

    private PointHistoryEntity toHistoryEntity(PointCommand pointCommand, PointHistoryEntity.Type type) {
        return new PointHistoryEntity(
                pointCommand.getUserId(),
                pointCommand.getPoint(),
                type
        );
    }
}