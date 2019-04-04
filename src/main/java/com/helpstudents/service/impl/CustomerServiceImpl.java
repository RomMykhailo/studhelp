package com.helpstudents.service.impl;

import com.helpstudents.config.jwt.JvtTokenProvider;
import com.helpstudents.domain.CustomerDTOForAdmin;
import com.helpstudents.entity.CustomerEntity;
import com.helpstudents.exeptions.ExistsExceptions;
import com.helpstudents.repository.CustomerRepository;
import com.helpstudents.service.CustomerService;
import com.helpstudents.utils.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;

    private boolean  existsCustomer (Long id){
       return customerRepository.existsById(id);
    }
    @Override
    public List<CustomerDTOForAdmin> getAllCustomers() {
        List<CustomerEntity> customerEntity = customerRepository.findAll();
        List<CustomerDTOForAdmin> customerDTOForAdmins = objectMapperUtils.mapAll(customerEntity, CustomerDTOForAdmin.class);
        return customerDTOForAdmins;
    }

    @Override
    public void deleteCustomerById(Long id) {
        if(!existsCustomer(id)){
            throw new ExistsExceptions("Customer with id " + id + " not exists");
        }
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        customerEntity.setRole(null);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDTOForAdmin getCustomerByToken(String token) {
        CustomerDTOForAdmin customerDTOForAdmin;
        String email = jvtTokenProvider.getUsernameFromToken(token);
        if(customerRepository.existsByEmailIgnoreCase(email)){
            CustomerEntity customerEntity = customerRepository.findByEmailIgnoreCase(email).get();
            customerDTOForAdmin = objectMapperUtils.map(customerEntity, CustomerDTOForAdmin.class);
        }else{
            throw new ExistsExceptions("Customer with email " + email + " not exists");
        }
        return customerDTOForAdmin;
    }

    @Override
    public CustomerDTOForAdmin getCustomerById(Long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).get();
        CustomerDTOForAdmin customerDTOForAdmin = objectMapperUtils.map(customerEntity, CustomerDTOForAdmin.class);
        return customerDTOForAdmin;
    }
}
