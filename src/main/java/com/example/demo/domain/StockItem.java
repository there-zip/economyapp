package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_items")
@Getter   //모든 변수에 getter 생성 (getSymbol()이나 getName() 자동 생성)
@NoArgsConstructor //빈 생성자 생성 (스프링은 DB에서 데이터 꺼낼 때 빈 객체를 만들어야 됨)
public class StockItem {

    @Id
    private String symbol; // 종목 코드 (종목코드가 숫자 문자 혼합이라 string 사용)

    @Column(nullable = false)
    private String name; // 종목 이름

    @Column(name = "market_type", nullable = false)
    private String marketType; // 시장 구분 (미장 / 국장)

    @Column(name = "current_price")
    private Double currentPrice; // 현재 주가
}
