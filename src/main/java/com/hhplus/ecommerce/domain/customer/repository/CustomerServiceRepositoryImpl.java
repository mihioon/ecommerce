package com.hhplus.ecommerce.domain.customer.repository;

import com.hhplus.ecommerce.domain.customer.model.Customer;
import com.hhplus.ecommerce.domain.customer.model.CustomerAmountHistory;
import com.hhplus.ecommerce.infrastructure.customer.CustomerAmountHistoryJpaRepository;
import com.hhplus.ecommerce.infrastructure.customer.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerServiceRepositoryImpl implements CustomerServiceRepository {
    private final CustomerJpaRepository customerJpaRepo;
    private final CustomerAmountHistoryJpaRepository historyJpaRepo;

    @Autowired
    public CustomerServiceRepositoryImpl(CustomerJpaRepository customerJpaRepo, CustomerAmountHistoryJpaRepository historyJpaRepo) {
        this.customerJpaRepo = customerJpaRepo;
        this.historyJpaRepo = historyJpaRepo;
    }

    @Override
    public Customer findCustomerByCustomerId(Long customerId) {
        Optional<Customer> customerOptional = customerJpaRepo.findById(customerId);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new RuntimeException("해당 사용자는 존재하지 않습니다.");
        }
    }

    @Override
    public List<CustomerAmountHistory> findHistoryByCustomerId(Long customerId) {
        return historyJpaRepo.findByCustomerId(customerId);
    }

    @Override
    public CustomerAmountHistory saveAmountHistory(CustomerAmountHistory customerAmountHistory) {
        return historyJpaRepo.save(customerAmountHistory);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerJpaRepo.save(customer);
    }
}
