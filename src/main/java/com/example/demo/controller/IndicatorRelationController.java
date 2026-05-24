package com.example.demo.controller;

import com.example.demo.domain.IndicatorRelation;
import com.example.demo.repository.IndicatorRelationRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class IndicatorRelationController {

    private final IndicatorRelationRepository indicatorRelationRepository;

    public IndicatorRelationController(IndicatorRelationRepository indicatorRelationRepository) {
        this.indicatorRelationRepository = indicatorRelationRepository;
    }

    @GetMapping("/relations")
    public List<IndicatorRelation> getAllRelations(
            @RequestParam(required = false) Long indicatorId) {
        if (indicatorId != null) {
            return indicatorRelationRepository.findByIndicatorId(indicatorId);
        }
        return indicatorRelationRepository.findAll();
    }

    @PostMapping("/relations")
    public IndicatorRelation createRelation(@RequestBody IndicatorRelation relation) {
        return indicatorRelationRepository.save(relation);
    }

    @DeleteMapping("/relations/{id}")
    public String deleteRelation(@PathVariable Long id) {
        indicatorRelationRepository.deleteById(id);
        return "삭제 완료: " + id;
    }
}