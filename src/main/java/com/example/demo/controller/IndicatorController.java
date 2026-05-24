package com.example.demo.controller;

import com.example.demo.domain.Indicator;
import com.example.demo.repository.IndicatorRepository;
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
public class IndicatorController {

    private final IndicatorRepository indicatorRepository;

    public IndicatorController(IndicatorRepository indicatorRepository) {
        this.indicatorRepository = indicatorRepository;
    }

    @GetMapping("/indicators")
    public List<Indicator> getAllIndicators(
            @RequestParam(required = false) String category) {
        if (category != null) {
            return indicatorRepository.findByCategory(category);
        }
        return indicatorRepository.findAll();
    }
    @GetMapping("/indicators/{id}")
    public Indicator getIndicatorById(@PathVariable Long id) {
        return indicatorRepository.findById(id).orElse(null);
    }

    @PostMapping("/indicators")
    public Indicator createIndicator(@RequestBody Indicator indicator) {
        return indicatorRepository.save(indicator);
    }

     @PutMapping("/indicators/{id}")
    public Indicator updateIndicator(@PathVariable Long id, @RequestBody Indicator request) {
        Indicator indicator = indicatorRepository.findById(id).orElse(null);
        if (indicator == null) return null;
        indicator.update(request.getName(), request.getCategory(), request.getCurrentValue());
        return indicatorRepository.save(indicator);
    }

     @DeleteMapping("/indicators/{id}")
    public String deleteIndicator(@PathVariable Long id) {
        indicatorRepository.deleteById(id);
        return "삭제 완료: " + id;
    }
}
