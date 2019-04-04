package com.helpstudents.controller;

import com.helpstudents.config.AppConstants;
import com.helpstudents.domain.CustomerDTOForAdmin;
import com.helpstudents.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity getAllCustomers (){
        List<CustomerDTOForAdmin> customerDTOForAdmins = customerService.getAllCustomers();
        return new ResponseEntity(customerDTOForAdmins, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/deactivate/{id}")
    public ResponseEntity<?> deactivateCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/getByToken")
    public ResponseEntity<?> getWorkerByToken (@RequestHeader(value = "Authorization") String token){
        String token2 = token.replace(AppConstants.TOKEN_PREFIX,"");
        CustomerDTOForAdmin customerDTOForAdmin= customerService.getCustomerByToken(token2);
        return  new ResponseEntity<>(customerDTOForAdmin,HttpStatus.OK);
    }

}
