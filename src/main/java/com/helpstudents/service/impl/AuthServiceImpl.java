package com.helpstudents.service.impl;

import com.helpstudents.config.jwt.JvtTokenProvider;
import com.helpstudents.domain.*;
import com.helpstudents.entity.AdminEntity;
import com.helpstudents.entity.CustomerEntity;
import com.helpstudents.entity.RoleEntity;
import com.helpstudents.entity.WorkerEntity;
import com.helpstudents.exeptions.ExistsExceptions;
import com.helpstudents.repository.AdminRepository;
import com.helpstudents.repository.CustomerRepository;
import com.helpstudents.repository.RoleRepository;
import com.helpstudents.repository.WorkerRepository;
import com.helpstudents.service.AuthService;
import com.helpstudents.utils.ObjectMapperUtils;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapperUtils objectMapperUtils;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JvtTokenProvider jvtTokenProvider;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;

    private boolean existsUserByEmail(String email){
        boolean exists1 = adminRepository.existsByEmailIgnoreCase(email);
        boolean exists2 = workerRepository.existsByEmailIgnoreCase(email);
        boolean exists3 = customerRepository.existsByEmailIgnoreCase(email);
        return exists1||exists2||exists3;
    }

    @Override
    public void registerWorker(WorkerDTO workerDTO)  {
        if(existsUserByEmail(workerDTO.getEmail())){
            throw new ExistsExceptions("Worker with email " + workerDTO.getEmail() + " already exists");
        }else if(!workerDTO.getPassword().equals(workerDTO.getPasswordConfirm())){
            throw new ExistsExceptions("Pasword and passwordConfirm not match");
        }
        String password = workerDTO.getPassword();
        workerDTO.setPassword(passwordEncoder.encode(password));
        WorkerEntity workerEntity = objectMapperUtils.map(workerDTO, WorkerEntity.class);
        workerRepository.save(workerEntity);
    }

    @Override
    public String loginWorker(SigninWorkerDTO request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jvtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public void registerCustomer(CustomerDTO customerDTO) throws NotFoundException {
        if(customerRepository.existsByEmailIgnoreCase(customerDTO.getEmail())){
            throw new ExistsExceptions("Customer with email " + customerDTO.getEmail() + " already exists");
        }else if(!customerDTO.getPassword().equals(customerDTO.getPasswordConfirm())){
            throw new ExistsExceptions("Pasword and passwordConfirm not match");
        }
        String password = customerDTO.getPassword();
        customerDTO.setPassword(passwordEncoder.encode(password));
        CustomerEntity customerEntity = objectMapperUtils.map(customerDTO, CustomerEntity.class);
        RoleEntity roleEntity = roleRepository.findByRole("CUSTOMER")
                .orElseThrow(()-> new NotFoundException("Role not found")
                );
        customerEntity.setRole(roleEntity);
        customerRepository.save(customerEntity);
    }

    @Override
    public String loginCustomer(SigninCustomerDTO signinCustomerDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinCustomerDTO.getEmail(), signinCustomerDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jvtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public void registerAdmin(AdminDTO adminDTO) throws NotFoundException {
        if(existsUserByEmail(adminDTO.getEmail())){
            throw new ExistsExceptions("Admin with email " + adminDTO.getEmail() + " already exists");
        }else if(!adminDTO.getPassword().equals(adminDTO.getPasswordConfirm())){
            throw new ExistsExceptions("Pasword and passwordConfirm not match");
        }
        String password = adminDTO.getPassword();
        adminDTO.setPassword(passwordEncoder.encode(password));
        AdminEntity adminEntity = objectMapperUtils.map(adminDTO, AdminEntity.class);
        RoleEntity roleEntity1 = roleRepository.findByRole("ADMIN")
                .orElseThrow(()-> new NotFoundException("Role not found")
                );
        RoleEntity roleEntity2 = roleRepository.findByRole("WORKER").get();
        RoleEntity roleEntity3 = roleRepository.findByRole("CUSTOMER").get();
        adminEntity.setRoles(Arrays.asList(roleEntity1, roleEntity2, roleEntity3));
    }

    @Override
    public String loginAdmin(SigninAdminDTO signinAdminDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinAdminDTO.getEmail(), signinAdminDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jvtTokenProvider.generateToken(authentication);
        return token;
    }
}
