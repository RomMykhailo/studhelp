package com.helpstudents.controller;

import com.helpstudents.domain.*;
import com.helpstudents.service.AuthService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("signupworker")
    public ResponseEntity registerWorker (@Valid @RequestBody WorkerDTO workerDTO) throws NotFoundException {
        authService.registerWorker(workerDTO);
        return new  ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("signinworker")
    public ResponseEntity loginWorker (@Valid @RequestBody SigninWorkerDTO signinWorkerDTO){
        String token = authService.loginWorker(signinWorkerDTO);
        return  new ResponseEntity(token, HttpStatus.OK);
    }
    @PostMapping("signinadmin")
    public ResponseEntity loginAdmin (@Valid @RequestBody SigninAdminDTO singinAdminDTO){
        String token = authService.loginAdmin(singinAdminDTO);
        return new ResponseEntity(token, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_MAINADMIN')")
    @PostMapping("signupadmin")
    public ResponseEntity registerAdmin (@Valid @RequestBody AdminDTO adminDTO) throws NotFoundException {
        authService.registerAdmin(adminDTO);
        return new  ResponseEntity(HttpStatus.CREATED);
    }
    @PostMapping("signincustomer")
    public ResponseEntity loginCustomer (@Valid @RequestBody SigninCustomerDTO signinCustomerDTO){
        String token = authService.loginCustomer(signinCustomerDTO);
        return new ResponseEntity(token, HttpStatus.OK);
    }

    @PostMapping("signupcustomer")
    public ResponseEntity registerCustomer (@Valid @RequestBody CustomerDTO customerDTO) throws NotFoundException {
        authService.registerCustomer(customerDTO);
        return new  ResponseEntity(HttpStatus.CREATED);
    }
}
