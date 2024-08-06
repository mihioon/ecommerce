package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.Order;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderRepository;
import com.hhplus.ecommerce.domain.order.OrderSheet;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepo;
    private final OrderProductJpaRepository orderProductJpaRepo;
    private final OrderSheetJpaRepository orderSheetJpaRepo;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepo, OrderProductJpaRepository orderProductJpaRepo, OrderSheetJpaRepository orderSheetJpaRepo, OrderSheetJpaRepository orderSheetJpaRepo1) {
        this.orderJpaRepo = orderJpaRepo;
        this.orderProductJpaRepo = orderProductJpaRepo;
        this.orderSheetJpaRepo = orderSheetJpaRepo;
    }

    // 주문 조회
    @Override
    public Order findOrder(Long orderId) {
        Optional<OrderEntity> orderEntity = orderJpaRepo.findById(orderId);
        return orderEntity.map(this::toDomain).orElse(null);
    }

    // 주문 생성
    @Override
    public Long persistOrder(Order order) {
        return orderJpaRepo.save(toEntity(order)).getId();
    }

    // 주문 제품 생성
    @Override
    public void persistOrderProducts(Long orderId, List<OrderProduct> orderProducts) {
        for(OrderProduct orderProduct : orderProducts){
            orderProductJpaRepo.save(toEntity(orderProduct, orderId));
        }
    }

    // 주문서 조회
    @Override
    public OrderSheet findOrderSheet(Long orderSheetId) {
        Optional<OrderSheetEntity> orderSheetEntity = orderSheetJpaRepo.findById(orderSheetId);
        return orderSheetEntity.map(this::toDomain).orElse(null);
    }


    // 주문 조회
    @Override
    public List<OrderProduct> findOrderProducts(Long orderId) {
        return orderProductJpaRepo.findByOrderId(orderId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Long saveOrderSheet(OrderSheet orderSheet) {
        OrderSheetEntity orderSheetEntity = toEntity(orderSheet);
        orderSheetEntity = orderSheetJpaRepo.save(orderSheetEntity);
        return orderSheetEntity.getId();
    }

    /**
     * mapper
     * */
    // 엔티티 > 도메인 변환
    private Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getOrderCode(), /* Key */
                orderEntity.getCustomerId(),
                orderEntity.getOrderState(), /* 주문 상태 */
                orderEntity.getTotalPrice(), /* 총 주문금액 */
                null
        );
    }

    // 도메인 > 엔티티 변환
    private OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getOrderCode(),
                order.getCustomerId(),
                LocalDateTime.now(),
                Order.State.NEW,
                order.getTotalPrice() /* 총 주문금액 */
        );
    }

    // 엔티티 > 도메인 변환
    private OrderProduct toDomain(OrderProductEntity orderProductEntity) {
        return new OrderProduct(
                orderProductEntity.getOrderId(),
                orderProductEntity.getProductId(),
                null,
                orderProductEntity.getFinalPrice(),
                orderProductEntity.getDiscountRate(),
                orderProductEntity.getQuantity()
        );
    }

    // 도메인 > 엔티티 변환
    private OrderProductEntity toEntity(OrderProduct orderProduct, Long orderId) {
        return new OrderProductEntity(
                orderId,
                orderProduct.getProductId(),
                orderProduct.getQuantity(), /* 주문 상태 */
                orderProduct.getFinalPrice() /* 총 주문금액 */
        );
    }

    // 엔티티 > 도메인 변환
    private OrderSheet toDomain(OrderSheetEntity orderSheetEntity) {
        return new OrderSheet(
                orderSheetEntity.getId(),
                null,
                orderSheetEntity.getCustomerId(),
                orderSheetEntity.getTotalAmount(),
                null
        );
    }

    // 도메인 > 엔티티 변환
    private OrderSheetEntity toEntity(OrderSheet orderSheet) {
        return new OrderSheetEntity(
                orderSheet.getId(),
                orderSheet.getCustomerId(),
                orderSheet.getTotalAmount()
        );
    }
}
