package com.hhplus.ecommerce.business.customer.repository;

import com.hhplus.ecommerce.business.customer.service.mok.CustomerAmountHistoryJpaRepository;
import com.hhplus.ecommerce.business.customer.service.mok.CustomerJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class CustomerServiceRepositoryImplTest {
    @InjectMocks
    private CustomerServiceRepositoryImpl customerServiceRepository;

    // Mock 객체 생성
    @Mock
    private CustomerAmountHistoryJpaRepository customerAmountHistoryJpaRepository;
    @Mock
    private CustomerJpaRepository customerJpaRepository;


    @BeforeEach
    public void setUp() {

    }
}