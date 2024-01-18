package com.example.aiyl_bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ClubRequest {
    private String name;
    private BigDecimal price;

    public ClubRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }
}