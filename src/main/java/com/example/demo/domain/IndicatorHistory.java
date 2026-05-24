package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "indicator_history")
@Getter
@NoArgsConstructor
public class IndicatorHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "indicator_id", nullable = false)
    private Long indicatorId; // 어떤 지표인지 (Indicator 테이블의 id)

    @Column(nullable = false)
    private LocalDate date; // 기록 날짜

    @Column(name = "indicator_value", nullable = false)
    private Double indicatorValue; // 그날의 지표 수치
}