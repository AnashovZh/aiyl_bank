package com.example.aiyl_bank.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "clubs")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "club_gen")
    @SequenceGenerator(name = "club_gen",
            sequenceName = "club_seg", allocationSize = 1)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    private Country country;
    @OneToMany(mappedBy = "club")
    private List<Footballer> footballers;

    public Club(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}