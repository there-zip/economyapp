package com.example.demo.repository;

import com.example.demo.domain.IndicatorHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorHistoryRepository extends JpaRepository<IndicatorHistory, Long> {
//나중에 요기 API 추가되면 특정 지표의 과거 기록 전체 조회같은 기능 추가할 예정
}