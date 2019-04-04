package com.helpstudents.repository;

import com.helpstudents.entity.RoleEntity;
import com.helpstudents.entity.WorkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<WorkerEntity, Long> {
    boolean existsByEmailIgnoreCase(String email);

    Optional<WorkerEntity> findByEmailIgnoreCase(String Email);

    Optional<WorkerEntity> findByNickName (String nickName);
    List<WorkerEntity> findAllByRolesNull();
}
