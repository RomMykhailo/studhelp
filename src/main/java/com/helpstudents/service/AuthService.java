package com.helpstudents.service;

import com.helpstudents.domain.*;
import javassist.NotFoundException;

public interface AuthService {
    void registerWorker (WorkerDTO workerDTO) throws NotFoundException;
    String loginWorker (SigninWorkerDTO workerDTO);

    void registerCustomer (CustomerDTO customerDTO) throws NotFoundException;
    String loginCustomer (SigninCustomerDTO signinCustomerDTO);

    void registerAdmin (AdminDTO adminDTO) throws NotFoundException;
    String loginAdmin (SigninAdminDTO signinAdminDTO);
}
