package com.example.aiyl_bank.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "country_gen")
    @SequenceGenerator(name = "country_gen",
            sequenceName = "country_seg", allocationSize = 1)
    private Long id;
    private String name;
    private int population;
    @OneToMany(mappedBy = "country")
    private List<Club> club;
}