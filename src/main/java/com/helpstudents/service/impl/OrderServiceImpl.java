package com.helpstudents.service.impl;

import com.helpstudents.config.jwt.JvtTokenProvider;
import com.helpstudents.domain.OrderDTO;
import com.helpstudents.domain.OrderResponseDTO;
import com.helpstudents.entity.CustomerEntity;
import com.helpstudents.entity.OrderEntity;
import com.helpstudents.repository.CustomerRepository;
import com.helpstudents.repository.OrderRepository;
import com.helpstudents.service.CustomerService;
import com.helpstudents.service.OrderService;
import com.helpstudents.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO, String token) {
        orderDTO.setDateCreate(LocalDateTime.now());
        OrderEntity orderEntity = objectMapperUtils.map(orderDTO, OrderEntity.class);
        String emailCustomer = jvtTokenProvider.getUsernameFromToken(token);
        CustomerEntity customerEntity = customerRepository.findByEmailIgnoreCase(emailCustomer).get();
        orderEntity.setCustomerEntity(customerEntity);
        orderRepository.save(orderEntity);
        return orderDTO;
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id).get();
        OrderResponseDTO orderDTO = objectMapperUtils.map(orderEntity,OrderResponseDTO.class);
        return orderDTO;
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersForCustomerId(Long customerId) {
        List<OrderEntity> orderEntities = orderRepository.findAllByCustomerEntityId(customerId);
        List<OrderResponseDTO> orderDTOS = objectMapperUtils.mapAll(orderEntities,OrderResponseDTO.class);
        return orderDTOS;
    }

    @Override
    public List<OrderResponseDTO> getAllOrdersForCustomerToken(String token) {
        String emailCustomer = jvtTokenProvider.getUsernameFromToken(token);
        List<OrderEntity> orderEntities = orderRepository.findAllByCustomerEntityEmail(emailCustomer);
        List<OrderResponseDTO> orderDTOS = objectMapperUtils.mapAll(orderEntities, OrderResponseDTO.class);
        return orderDTOS;
    }
}
