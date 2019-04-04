package com.helpstudents.repository;

import com.helpstudents.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {
   boolean existsByEmailIgnoreCase(String email);

   Optional<CustomerEntity> findByEmailIgnoreCase(String email);

   List<CustomerEntity> findAllByEmailIgnoreCase(String email);
}
