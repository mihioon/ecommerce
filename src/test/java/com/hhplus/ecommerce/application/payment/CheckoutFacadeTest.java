package com.hhplus.ecommerce.application.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CheckoutFacadeTest {
    @Autowired
    CheckoutFacade checkoutFacade;

    @BeforeEach
    void setUp() {
    }
    @DisplayName("주문 결제 테스트")
    void orderPaymentUseCaseTest() {
        //given
        //아이디 1L 인 제품 1개 구매

        //when

        //then
    }

}