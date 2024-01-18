package com.example.aiyl_bank.dto;

import com.example.aiyl_bank.model.Footballer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class FootballerResponse{
    private Long id;
    private String firstName;
    private String email;
    private String password;
    private BigDecimal price;

    public FootballerResponse(Long id, String firstName, String email,
                              String password, BigDecimal price) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.price = price;
    }

    public static FootballerResponse build(Footballer footballer){
        FootballerResponse footballerResponse=new FootballerResponse();
        footballerResponse.setId(footballer.getId());
        footballerResponse.setFirstName(footballer.getFirstName());
        footballerResponse.setEmail(footballer.getEmail());
        footballerResponse.setPassword(footballer.getPassword());
        footballerResponse.setPrice(footballer.getPrice());
        return footballerResponse;
    }
}