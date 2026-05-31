package com.example.demo.repository;

import com.example.demo.domain.Indicator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface IndicatorRepository extends JpaRepository<Indicator, Long> {

    List<Indicator> findByCategory(String category);
    // 카테고리별 조회 (기존 코드)

    Optional<Indicator> findByName(String name);
    // 이름으로 지표 1개 조회 (새로 추가)
    // Optional = 찾은 결과가 있을 수도 없을 수도 있다는 뜻
    // 예: findByName("원/달러 환율") → 해당 지표 반환
}