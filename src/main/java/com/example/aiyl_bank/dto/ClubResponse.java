package com.example.aiyl_bank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ClubResponse{
    private Long id;
    private String name;
    private BigDecimal price ;

    public ClubResponse(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}