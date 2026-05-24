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

    @Column(name = "risk_score_weight")
    private Integer riskScoreWeight; // 최종 위험 가중치 점수
}