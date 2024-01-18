package com.example.aiyl_bank.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CountryResponse{
    private Long id;
    private String name;
    private int population;

    public CountryResponse(Long id, String name, int population) {
        this.id = id;
        this.name = name;
        this.population = population;
    }
}