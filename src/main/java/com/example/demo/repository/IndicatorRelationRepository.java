package com.example.demo.repository;

import com.example.demo.domain.IndicatorRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IndicatorRelationRepository extends JpaRepository<IndicatorRelation, Long> {
    List<IndicatorRelation> findByIndicatorId(Long indicatorId);
}

