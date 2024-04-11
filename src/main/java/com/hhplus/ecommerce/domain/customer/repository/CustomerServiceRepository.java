package com.hhplus.ecommerce.domain.customer.repository;


import com.hhplus.ecommerce.domain.customer.model.Customer;
import com.hhplus.ecommerce.domain.customer.model.CustomerAmountHistory;

import java.util.List;

public interface CustomerServiceRepository {
    Customer findCustomerByCustomerId(Long customerId);
    List<CustomerAmountHistory> findHistoryByCustomerId(Long customerId);
    CustomerAmountHistory saveAmountHistory(CustomerAmountHistory customerAmountHistory);
    Customer saveCustomer(Customer customer);

}
