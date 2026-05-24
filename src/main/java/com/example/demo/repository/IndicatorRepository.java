package com.example.demo.repository;

import com.example.demo.domain.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    List<Indicator> findByCategory(String category);
}