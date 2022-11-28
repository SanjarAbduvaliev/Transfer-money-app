package com.example.paymeapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String  cardNumber;
    @Column(nullable = false)
    private Date expireDate;
    private Double balance;
    @OneToMany
    private List<Output> outputs;
    @OneToMany
    private List<Income> income;


}
