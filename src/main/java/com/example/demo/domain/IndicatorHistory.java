package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime; 
//처음 클로드는 LocalData로 코드 짰는데 시간 단위로 데이터 긁어오고 싶어서 수정

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
     //Indicator 테이블의 id랑 연결되는 숫자임
    @Column(nullable = false)
    private LocalDateTime recordedAt; // 기록 날짜

    @Column(name = "indicator_value", nullable = false)
    private Double indicatorValue; // 그날의 지표 수치
}