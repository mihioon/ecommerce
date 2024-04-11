package com.hhplus.ecommerce.domain.customer.service.mok;

import com.hhplus.ecommerce.domain.customer.model.Customer;

import java.math.BigDecimal;

public class CustomerJpaRepository {
    public Customer save(){
        return Customer.builder().id(1L).amount(BigDecimal.ZERO).build();
    }
}
