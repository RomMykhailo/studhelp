package com.helpstudents.service.impl;

import com.helpstudents.entity.AdminEntity;
import com.helpstudents.entity.CustomerEntity;
import com.helpstudents.entity.WorkerEntity;
import com.helpstudents.repository.AdminRepository;
import com.helpstudents.repository.CustomerRepository;
import com.helpstudents.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService  {
    @Autowired
    private WorkerRepository workerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String password;
        List<SimpleGrantedAuthority> authoritys;

        if(adminRepository.existsByEmailIgnoreCase(email)){
            AdminEntity adminEntity = adminRepository.findByEmailIgnoreCase(email).get();
            password = adminEntity.getPassword();
            authoritys = getAuthoritiesAdmin(adminEntity);
        }else if(workerRepository.existsByEmailIgnoreCase(email)){
            WorkerEntity workerEntity = workerRepository.findByEmailIgnoreCase(email).get();
            password = workerEntity.getPassword();
            authoritys = getAuthoritiesWorker(workerEntity);
        }else if(customerRepository.existsByEmailIgnoreCase(email)){
            CustomerEntity customerEntity = customerRepository.findByEmailIgnoreCase(email).get();
            password = customerEntity.getPassword();
            authoritys = getAuthoritiesCustomer(customerEntity);
        }else {throw  new UsernameNotFoundException("User with this email [" + email + "] not found");
        }
        return User.builder()
                .username(email)
                .password(password)
                .authorities(authoritys)
                .build();
    }
    private List<SimpleGrantedAuthority> getAuthoritiesWorker (WorkerEntity workerEntity){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        workerEntity.getRoles().forEach(r-> {authorities.add(new SimpleGrantedAuthority("ROLE_"+ r.getRole()));});
        return authorities;
    }
    private List<SimpleGrantedAuthority> getAuthoritiesAdmin (AdminEntity adminEntity){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        adminEntity.getRoles().forEach(r->{authorities.add(new SimpleGrantedAuthority("ROLE_"+ r.getRole()));});
       return authorities;
    }
    private List<SimpleGrantedAuthority> getAuthoritiesCustomer (CustomerEntity customerEntity){
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + customerEntity.getRole().getRole()));
        return authorities;
    }
}