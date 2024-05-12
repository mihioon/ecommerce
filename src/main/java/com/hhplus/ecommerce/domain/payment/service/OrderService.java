package com.hhplus.ecommerce.domain.payment.service;

import com.hhplus.ecommerce.domain.customer.object.Customer;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.OrderDTO;
import com.hhplus.ecommerce.domain.payment.service.serviceDTO.PaymentDTO;
import com.hhplus.ecommerce.domain.product.service.serviceDTO.ProductDTO;

public interface OrderService {
    public OrderDTO saveOrder(Customer customer, PaymentDTO param);
    public OrderDTO updateOrder(Customer customer, OrderDTO param);
    public OrderDTO saveOrderList(Long orderNumber, ProductDTO[] param);
}
