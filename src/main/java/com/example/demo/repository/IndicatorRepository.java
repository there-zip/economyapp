package com.example.demo.repository;

import com.example.demo.domain.Indicator;
import org.springframework.data.jpa.repository.JpaRepository; //요기에 기본적인 CRUD 다 있음
import java.util.List;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {
    List<Indicator> findByCategory(String category); //JpaRepository에 카테고리 조회하는 기능 추가
}
