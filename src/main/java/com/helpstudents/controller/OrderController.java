package com.helpstudents.controller;

import com.helpstudents.config.AppConstants;
import com.helpstudents.domain.CustomerDTO;
import com.helpstudents.domain.CustomerDTOForAdmin;
import com.helpstudents.domain.OrderDTO;
import com.helpstudents.domain.OrderResponseDTO;
import com.helpstudents.service.CustomerService;
import com.helpstudents.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/add")
    public ResponseEntity<?> addOrder (@Valid @RequestBody OrderDTO orderDTO,
                                       @RequestHeader(value = "Authorization") String token){
        String token2 = token.replace(AppConstants.TOKEN_PREFIX,"");
       OrderDTO orderDTO1 = orderService.createOrder(orderDTO, token2);
       return new ResponseEntity<>(orderDTO1,HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ROLE_CUSTOMER, ROLE_ADMIN, ROLE_WORKER')")
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id){
        OrderResponseDTO order = orderService.getOrderById(id);
        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @GetMapping("/getAllMyOrder")
    public ResponseEntity<?> getAllOrdersForCustomerId(@RequestHeader(value = "Authorization") String token){
        String token2 = token.replace(AppConstants.TOKEN_PREFIX,"");
        List<OrderResponseDTO> orders = orderService.getAllOrdersForCustomerToken(token2);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }


}
