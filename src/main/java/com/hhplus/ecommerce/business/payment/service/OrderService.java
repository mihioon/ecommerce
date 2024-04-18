package com.hhplus.ecommerce.business.payment.service;

import com.hhplus.ecommerce.business.customer.model.Customer;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.OrderDTO;
import com.hhplus.ecommerce.business.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.business.product.service.serviceDTO.ProductDTO;

import java.util.List;

public interface OrderService {
    public OrderDTO saveOrder(Customer customer, PaymentDTO param);
    public OrderDTO updateOrder(Customer customer, OrderDTO param);
    public OrderDTO saveOrderList(Long orderNumber, ProductDTO[] param);
}
