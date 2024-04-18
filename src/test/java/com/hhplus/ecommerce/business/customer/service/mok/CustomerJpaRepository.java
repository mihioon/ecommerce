package com.hhplus.ecommerce.business.customer.service.mok;

import com.hhplus.ecommerce.business.customer.model.Customer;

import java.math.BigDecimal;
import java.util.Optional;

public class CustomerJpaRepository {

    public Optional<Customer> findById(Long customerId){
        Optional<Customer> result;
        //noCustomer
        if(customerId == 0L){
            result = Optional.empty();
        } else { //yesCustomer
            Customer customer = Customer.builder().id(1L).amount(BigDecimal.TEN).build();
            result = Optional.of(customer);
        }
        return result;
    }

    public Customer save(Customer customer){
        return customer;
    }
}
