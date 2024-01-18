package com.example.aiyl_bank.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "footballers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Footballer  {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "footballer_gen")
    @SequenceGenerator(name = "footballer_gen",
            sequenceName = "footballer_seg", allocationSize = 1)
    private Long id;
    private String firstName;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal price;
    @ManyToOne
    private Club club;

    public Footballer(Long id, String firstName,
                      String email, String password, BigDecimal price) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.price = price;
    }
}