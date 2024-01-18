package com.example.aiyl_bank.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class FootballerRequest {
    private String firstName;
    private String email;
    private String password;
    private BigDecimal price;
}