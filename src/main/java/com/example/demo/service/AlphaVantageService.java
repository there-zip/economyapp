package com.example.demo.service;

import com.example.demo.domain.Indicator;
import com.example.demo.repository.IndicatorRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AlphaVantageService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final IndicatorRepository indicatorRepository;

    public AlphaVantageService(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    // 환율 데이터 가져와서 DB에 저장
    public void fetchAndSaveExchangeRate(String fromCurrency, String toCurrency) throws Exception {
        String url = String.format(
            "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=%s&to_currency=%s&apikey=%s",
            fromCurrency, toCurrency, apiKey
        );

        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        double exchangeRate = root
            .path("Realtime Currency Exchange Rate")
            .path("5. Exchange Rate")
            .asDouble();

        Indicator indicator = indicatorRepository.findByName("원/달러 환율")
            .orElseThrow(() -> new RuntimeException("지표를 찾을 수 없습니다."));
        indicator.update(indicator.getName(), indicator.getCategory(), exchangeRate);
        indicatorRepository.save(indicator);
    }

    // 원자재 가격 가져와서 DB에 저장 (유가, 금, 구리 등)
    public void fetchAndSaveCommodity(String commodityFunction, String indicatorName) throws Exception {
        String url = String.format(
            "https://www.alphavantage.co/query?function=%s&interval=monthly&apikey=%s",
            commodityFunction, apiKey
        );

        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);
        double price = root
            .path("data")
            .get(0)
            // 가장 최근 데이터 (배열의 첫 번째)
            .path("value")
            .asDouble();
        // 최신 가격 뽑아내기

        Indicator indicator = indicatorRepository.findByName(indicatorName)
            .orElseThrow(() -> new RuntimeException("지표를 찾을 수 없습니다: " + indicatorName));
        indicator.update(indicator.getName(), indicator.getCategory(), price);
        indicatorRepository.save(indicator);
    }

    // raw 데이터 확인용 (임시)
    public String getCommodityPrice(String commodity) {
        String url = String.format(
        "https://www.alphavantage.co/query?function=%s&interval=monthly&apikey=%s",
        commodity, apiKey
        );
        return restTemplate.getForObject(url, String.class);
    }
}