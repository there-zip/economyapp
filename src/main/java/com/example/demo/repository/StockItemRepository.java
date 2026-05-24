package com.example.demo.repository;

import com.example.demo.domain.StockItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StockItemRepository extends JpaRepository<StockItem, String> {
    List<StockItem> findByMarketType(String marketType);
}