package com.hhplus.ecommerce.infrastructure.order;

import com.hhplus.ecommerce.domain.order.Order;
import com.hhplus.ecommerce.domain.order.OrderProduct;
import com.hhplus.ecommerce.domain.order.OrderRepository;
import com.hhplus.ecommerce.domain.order.OrderSheet;
import com.hhplus.ecommerce.util.OrderState;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        OrderEntity orderEntity = orderJpaRepo.findById(orderId).orElseThrow(()
                -> new EntityNotFoundException("해당하는 주문 없음"));
        return toDomain(orderEntity);
    }

    // 주문 생성
    @Override
    public Long persistOrder(Order order) {
        return orderJpaRepo.save(toEntity(order)).getId();
    }

    // 주문 제품 생성
    @Override
    public void persistOrderProducts(List<OrderProduct> orderProducts) {
        if(orderProducts == null) return;
        for(OrderProduct orderProduct : orderProducts){
            orderProductJpaRepo.save(toEntity(orderProduct));
        }
    }

    // 주문서 조회
    @Override
    public OrderSheet findOrderSheet(Long orderSheetId) {
        OrderSheetEntity orderSheetEntity = orderSheetJpaRepo.findById(orderSheetId).orElseThrow(()
                -> new EntityNotFoundException("해당하는 주문 없음"));
        return toDomain(orderSheetEntity);
    }


    // 주문 조회
    @Override
    public List<OrderProduct> findOrderProducts(String orderId) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        List<OrderProductEntity> orderProductEntities = orderProductJpaRepo.findByOrderId(orderId);
        for(OrderProductEntity orderProductEntity : orderProductEntities){
            orderProducts.add(toDomain(orderProductEntity));
        }
        return orderProducts;
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
                orderEntity.getTotalPrice() /* 총 주문금액 */
        );
    }

    // 도메인 > 엔티티 변환
    private OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getOrderCode(),
                order.getCustomerId(),
                LocalDateTime.now(),
                OrderState.NEW,
                order.getTotalPrice() /* 총 주문금액 */
        );
    }

    // 엔티티 > 도메인 변환
    private OrderProduct toDomain(OrderProductEntity orderProductEntity) {
        return new OrderProduct(
                orderProductEntity.getOrderId(),
                orderProductEntity.getProductId(), /* Key */
                orderProductEntity.getProductAmount(),
                orderProductEntity.getFinalPrice(), /* 총 주문금액 */
                orderProductEntity.getDiscountRate(),
                orderProductEntity.getQuantity()
        );
    }

    // 도메인 > 엔티티 변환
    private OrderProductEntity toEntity(OrderProduct orderProduct) {
        return new OrderProductEntity(
                orderProduct.getProductId(),
                orderProduct.getQuantity(), /* 주문 상태 */
                orderProduct.getFinalPrice() /* 총 주문금액 */
        );
    }

    // 엔티티 > 도메인 변환
    private OrderSheet toDomain(OrderSheetEntity orderSheetEntity) {
        return new OrderSheet(
                orderSheetEntity.getId(), /* Key */
                orderSheetEntity.getCustomerId(),
                orderSheetEntity.getTotalAmount(),
                null
        );
    }
}
