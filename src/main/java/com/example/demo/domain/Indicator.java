package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "indicators")
@Getter
@NoArgsConstructor
public class Indicator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(name = "current_value")
    private Double currentValue;

    public void update(String name, String category, Double currentValue) {
        this.name = name;
        this.category = category;
        this.currentValue = currentValue;
    }
}