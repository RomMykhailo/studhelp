package com.helpstudents;

import com.helpstudents.entity.AdminEntity;
import com.helpstudents.entity.RoleEntity;
import com.helpstudents.repository.AdminRepository;
import com.helpstudents.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories()
public class StudentHelpApplication implements CommandLineRunner {
    public static void main(String[] args) {
         SpringApplication.run(StudentHelpApplication.class, args);
    }
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count()==0){
            RoleEntity roleMainAdmin = new RoleEntity("MAINADMIN");
            roleRepository.save(roleMainAdmin);
            RoleEntity roleAdmin = new RoleEntity("ADMIN");
            roleRepository.save(roleAdmin);
            RoleEntity roleWorker = new RoleEntity("WORKER");
            roleRepository.save(roleWorker);
            RoleEntity roleCustomer = new RoleEntity("CUSTOMER");
            roleRepository.save(roleCustomer);
        }
        String email = "Romanskiy.miha@ukr.net";
        if(!adminRepository.existsByEmailIgnoreCase(email)){
            AdminEntity adminEntity = new AdminEntity();
            adminEntity.setEmail(email);
            adminEntity.setPassword(passwordEncoder.encode("miha"));
            adminEntity.setFirstName("Mykhailo");
            adminEntity.setLastName("Romanskiy");
            adminEntity.setPhoneNumber("0978607298");
            List<RoleEntity> rols = new ArrayList<>();
            rols.add(roleRepository.findByRole("ADMIN").get());
            rols.add(roleRepository.findByRole("MAINADMIN").get());
            rols.add(roleRepository.findByRole("WORKER").get());
            rols.add(roleRepository.findByRole("CUSTOMER").get());
            adminEntity.setRoles(rols);
            adminRepository.save(adminEntity);
        }

    }
}

