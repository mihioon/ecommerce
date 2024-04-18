package com.hhplus.ecommerce.business.customer.service;

import com.hhplus.ecommerce.business.customer.model.Customer;
import com.hhplus.ecommerce.business.customer.model.CustomerAmountHistory;
import com.hhplus.ecommerce.business.customer.service.serviceDTO.CustomerDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerService {
    Customer findCustomerByCustomerId(Long customerId);
    List<CustomerAmountHistory> findHistoryByCustomerId(Long customerId);
    CustomerAmountHistory saveAmountHistory(CustomerDTO param, Customer customer);
    Customer updateChargeCustomer(Customer customer, BigDecimal chargeAmount);
}
