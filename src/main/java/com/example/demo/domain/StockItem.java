package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_items")
@Getter
@NoArgsConstructor
public class StockItem {

    @Id
    private String symbol; // 종목 코드 (예: AAPL, 005930)

    @Column(nullable = false)
    private String name; // 종목 이름 (예: 애플, 삼성전자)

    @Column(name = "market_type", nullable = false)
    private String marketType; // 시장 구분 (미장 / 국장)

    @Column(name = "current_price")
    private Double currentPrice; // 현재 주가
}
