package com.helpstudents.service;

import com.helpstudents.domain.CustomerDTOForAdmin;

import java.util.List;

public interface CustomerService {
    List<CustomerDTOForAdmin> getAllCustomers ();
    void deleteCustomerById(Long id);
    CustomerDTOForAdmin getCustomerByToken(String token);
    CustomerDTOForAdmin getCustomerById(Long id);
}
