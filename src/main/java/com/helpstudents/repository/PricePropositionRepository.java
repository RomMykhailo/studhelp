package com.helpstudents.repository;

import com.helpstudents.entity.PricePropositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricePropositionRepository extends JpaRepository<PricePropositionEntity,Long> {
}
