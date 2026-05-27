package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "indicator_relations")
@Getter
@NoArgsConstructor
public class IndicatorRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "indicator_id", nullable = false)
    private Long indicatorId; // 연결할 지표 id

    @Column(name = "stock_symbol", nullable = false)
    private String stockSymbol; // 연결할 종목 코드

    @Column
    private Double correlation; // 상관계수 (-1.0 ~ +1.0)
    // 요기 correlation은 우리가 나중에 알고리즘으로 어떻게 변환된는지 정할거임
    @Column(name = "risk_score_weight")
    private Integer riskScoreWeight; // 최종 위험 가중치 점수
    // 상관관계를 우리가 읽기 쉽게 점수로 변환해주는 역할
}