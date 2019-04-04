package com.helpstudents.repository;

import com.helpstudents.entity.RespondEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespondRepository extends JpaRepository<RespondEntity,Long> {
    List<RespondEntity> findAllByCustomerEntityId(Long id);
    List<RespondEntity> findAllByWorkerEntityId(Long id);

}
