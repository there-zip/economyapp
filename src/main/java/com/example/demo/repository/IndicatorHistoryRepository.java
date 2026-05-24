package com.example.demo.repository;

import com.example.demo.domain.IndicatorHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorHistoryRepository extends JpaRepository<IndicatorHistory, Long> {
}