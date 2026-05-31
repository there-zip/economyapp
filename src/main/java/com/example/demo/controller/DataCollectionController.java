package com.example.demo.controller;

import com.example.demo.service.AlphaVantageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataCollectionController {

    private final AlphaVantageService alphaVantageService;

    public DataCollectionController(AlphaVantageService alphaVantageService) {
        this.alphaVantageService = alphaVantageService;
    }

    @GetMapping("/collect/exchange-rate")
    public String collectExchangeRate() {
        try {
            alphaVantageService.fetchAndSaveExchangeRate("USD", "KRW");
            return "환율 데이터 수집 및 저장 완료!";
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

    @GetMapping("/collect/wti")
    public String collectWTI() {
        try {
            alphaVantageService.fetchAndSaveCommodity("WTI", "국제 유가(WTI)");
            return "유가 데이터 수집 및 저장 완료!";
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

    @GetMapping("/collect/copper")
    public String collectCopper() {
        try {
            alphaVantageService.fetchAndSaveCommodity("COPPER", "구리 선물 가격");
            return "구리 가격 데이터 수집 및 저장 완료!";
        } catch (Exception e) {
            return "에러 발생: " + e.getMessage();
        }
    }

    @GetMapping("/collect/all")
    public String collectAll() throws InterruptedException {
        StringBuilder result = new StringBuilder();
        try {
            alphaVantageService.fetchAndSaveExchangeRate("USD", "KRW");
            result.append("환율 ✅ ");
        } catch (Exception e) {
            result.append("환율 ❌ (").append(e.getMessage()).append(") ");
        }

        Thread.sleep(15000);
        // 15초 대기 (Alpha Vantage 무료 플랜 분당 5회 제한 때문에)

        try {
            alphaVantageService.fetchAndSaveCommodity("WTI", "국제 유가(WTI)");
            result.append("유가 ✅ ");
        } catch (Exception e) {
            result.append("유가 ❌ (").append(e.getMessage()).append(") ");
        }

        Thread.sleep(15000);
        // 15초 대기

        try {
            alphaVantageService.fetchAndSaveCommodity("COPPER", "구리 선물 가격");
            result.append("구리 ✅ ");
        } catch (Exception e) {
            result.append("구리 ❌ (").append(e.getMessage()).append(") ");
        }

        return "전체 수집 완료: " + result.toString();
    }
}