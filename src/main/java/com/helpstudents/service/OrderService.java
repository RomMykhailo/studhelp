package com.helpstudents.service;

import com.helpstudents.domain.OrderDTO;
import com.helpstudents.domain.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder (OrderDTO orderDTO, String token);
    OrderResponseDTO getOrderById (Long id);
    List<OrderResponseDTO> getAllOrdersForCustomerId (Long customerId);
    List<OrderResponseDTO> getAllOrdersForCustomerToken (String token);


}
