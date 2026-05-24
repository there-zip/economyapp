package com.example.demo.controller;

import com.example.demo.domain.StockItem;
import com.example.demo.repository.StockItemRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class StockItemController {

    private final StockItemRepository stockItemRepository;

    public StockItemController(StockItemRepository stockItemRepository) {
        this.stockItemRepository = stockItemRepository;
    }

    @GetMapping("/stocks")
    public List<StockItem> getAllStocks(
            @RequestParam(required = false) String marketType) {
        if (marketType != null) {
            return stockItemRepository.findByMarketType(marketType);
        }
        return stockItemRepository.findAll();
    }

    @GetMapping("/stocks/{symbol}")
    public StockItem getStockBySymbol(@PathVariable String symbol) {
        return stockItemRepository.findById(symbol).orElse(null);
    }

    @PostMapping("/stocks")
    public StockItem createStock(@RequestBody StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    @DeleteMapping("/stocks/{symbol}")
    public String deleteStock(@PathVariable String symbol) {
        stockItemRepository.deleteById(symbol);
        return "삭제 완료: " + symbol;
    }
}