package com.hhplus.ecommerce.business.customer.service;

import com.hhplus.ecommerce.business.customer.model.Customer;
import com.hhplus.ecommerce.business.customer.model.CustomerAmountHistory;
import com.hhplus.ecommerce.business.customer.service.mok.CustomerJpaRepository;
import com.hhplus.ecommerce.business.customer.service.serviceDTO.CustomerDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerJpaRepository customerJpaRepository;

    CustomerDTO customerDTO = CustomerDTO.builder()
            .customerId(1L)
            .chargeAmount(BigDecimal.TEN)
            .build();

    Customer customer = Customer.builder().id(1L).amount(BigDecimal.TEN).build();

    CustomerAmountHistory customerAmountHistory = CustomerAmountHistory.builder()
            .customer(customer)
            .type(customerDTO.getType())
            .amount(customerDTO.getChargeAmount())
            .build();

    @BeforeEach
    public void setUp() {
    }


    @Test
    void saveCustomerAmount사용자가없는경우() {
        lenient().when(customerServiceRepository.findCustomerByCustomerId(1L))
                .thenThrow( new RuntimeException("해당 사용자는 존재하지 않습니다."));
        lenient().when(customerServiceRepository.saveAmountHistory(customerAmountHistory))
                .thenReturn(customerAmountHistory);

        assertThrows(RuntimeException.class, () -> customerService.saveCustomerAmount(customerDTO));
    }

    @Test
    void saveCustomerAmount사용자가있는경우() {
        lenient().when(customerServiceRepository.findCustomerByCustomerId(1L)).thenReturn(customer);
        lenient().when(customerServiceRepository.saveAmountHistory(customerAmountHistory))
                .thenReturn(customerAmountHistory);

        CustomerDTO result = customerService.saveCustomerAmount(customerDTO);

        //assertThat(result.getCustomerId()).isEqualTo(1L);
        //assertThat(result.getAmount()).isEqualTo(BigDecimal.TEN);
    }


}